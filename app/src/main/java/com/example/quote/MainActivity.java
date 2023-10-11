package com.example.quote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quote.Database.QouteDatabase;
import com.example.quote.Database.Quote;
import com.example.quote.Api.RetrofitQuote;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btn_saved;
    ImageButton btn_save, btn_share;
    TextView tv_quote, tv_author;
    Quote quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_saved = findViewById(R.id.btn_saved);
        btn_save = findViewById(R.id.btn_save);
        btn_share = findViewById(R.id.btn_share);
        tv_quote = findViewById(R.id.tv_quote);
        tv_author = findViewById(R.id.tv_author);


        Call<Quote> quoteCall= RetrofitQuote.getInstance().getRandomQuote();

        quoteCall.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {

                quote = response.body();
                tv_quote.setText("“" + quote.getContent() + "”");
                tv_author.setText("- " + quote.getAuthor());
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                Toast.makeText(MainActivity.this
                        , "No Internet Connection"
                        , Toast.LENGTH_SHORT).show();
                tv_quote.setText("No Internet Connection");
                tv_author.setText("Quote App");
            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(quote != null) {
                    String sharedQuote = "\"" + quote.getContent() + "\"\n" + "- " + quote.getAuthor();

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, sharedQuote);
                    sendIntent.setType("text/plain");

                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quote != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            QouteDatabase.getInstance(MainActivity.this).quoteDAO().insertQuote(quote);
                        }
                    }).start();
                    btn_save.setBackgroundResource(R.drawable.ic_bookmark_filled);
                }
            }
        });

        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SavedQuotesActivity.class));
            }
        });
    }
}