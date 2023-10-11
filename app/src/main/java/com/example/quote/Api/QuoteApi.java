package com.example.quote.Api;


import com.example.quote.Database.Quote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteApi {
    @GET("random")
    Call<Quote>getRandomQuote();
}
