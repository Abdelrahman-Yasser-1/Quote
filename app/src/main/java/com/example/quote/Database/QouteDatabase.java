package com.example.quote.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Quote.class}, version = 1)
public abstract class QouteDatabase extends RoomDatabase {

    public abstract QuoteDAO quoteDAO();
    private static volatile QouteDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static QouteDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (QouteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    QouteDatabase.class, "quotes_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
