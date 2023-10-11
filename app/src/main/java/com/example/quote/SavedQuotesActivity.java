package com.example.quote;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quote.Adapter.OnRecyclerViewButtonClickListener;
import com.example.quote.Adapter.QuotesAdapter;
import com.example.quote.Database.QouteDatabase;
import com.example.quote.Database.Quote;

import java.util.ArrayList;

public class SavedQuotesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    QuotesAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    QouteDatabase db;
    ArrayList<Quote> quotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_quotes);

        recyclerView = findViewById(R.id.rv_saved);


        db = QouteDatabase.getInstance(this);
        db.quoteDAO().getAllQuotes().observe(this, quotes -> {
            this.quotes = (ArrayList<Quote>) quotes;
            adapter.setData(this.quotes);
        });

        adapter = new QuotesAdapter(this, quotes, new OnRecyclerViewButtonClickListener() {
            @Override
            public void onButtinClick(int position) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.quoteDAO().deleteQuote(quotes.get(position));
                    }
                }).start();

            }
        });

        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }
}