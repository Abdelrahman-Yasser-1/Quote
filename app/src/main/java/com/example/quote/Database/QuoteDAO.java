package com.example.quote.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuoteDAO {

    @Insert
    void insertQuote(Quote quote);

    @Delete
    void deleteQuote(Quote quote);

    @Query("SELECT * FROM quotes")
    LiveData<List<Quote>> getAllQuotes();

}
