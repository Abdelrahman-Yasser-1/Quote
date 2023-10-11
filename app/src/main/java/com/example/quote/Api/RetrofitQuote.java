package com.example.quote.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitQuote {

    private static final String BASE_URL = "https://api.quotable.io/";
    public static QuoteApi INSTANCE;

    public static QuoteApi getInstance() {
        if (INSTANCE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            INSTANCE = retrofit.create(QuoteApi.class);
        }
        return INSTANCE;
    }

}
