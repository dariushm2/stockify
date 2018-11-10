package com.dariushm2.stockify.remote


import com.dariushm2.stockify.model.Quote
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuoteService {
    @GET("stock/{symbol}/quote")
    fun getQuote(@Path("symbol") symbol: String): Call<Quote>
}
