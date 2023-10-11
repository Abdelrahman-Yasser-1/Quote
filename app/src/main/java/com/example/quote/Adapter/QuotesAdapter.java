package com.example.quote.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quote.Database.Quote;
import com.example.quote.R;

import java.util.ArrayList;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder> {

    private ArrayList<Quote> data;
    private static Context context;
    private static OnRecyclerViewButtonClickListener listener;

    public QuotesAdapter(Context context, ArrayList<Quote> data, OnRecyclerViewButtonClickListener listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    public void setData(ArrayList<Quote> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(context).inflate(R.layout.quote_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        // access view from ViewHolder like this (holder.componentName)

        Quote Quote = data.get(position);

        holder.quote.setText(Quote.getContent());
        holder.author.setText(Quote.getAuthor());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView quote, author;
        ImageButton btn_un_save;

        public ViewHolder(View view) {
            super(view);

            quote = view.findViewById(R.id.tv_quote);
            author = view.findViewById(R.id.tv_author);

            btn_un_save = view.findViewById(R.id.btn_un_save);
            btn_un_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onButtinClick(getAdapterPosition());
                }
            });

        }
    }
}
