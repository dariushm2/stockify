package com.dariushm2.stockify.remote


import com.dariushm2.stockify.model.Deep
import com.dariushm2.stockify.model.Quote
import com.dariushm2.stockify.model.Symbol
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StockServices {

    @GET("ref-data/symbols")
    suspend fun getSymbols(@Query("token") token: String = RemoteApi.token): Response<List<Symbol>>

    @GET("stock/{symbol}/quote")
    suspend fun getQuote(@Path("symbol") symbol: String,
                         @Query("token") token: String = RemoteApi.token): Response<Quote>

    @GET("/deep?symbols={symbol}")
    suspend fun getDeep(@Path("symbol") symbol: String,
                        @Query("token") token: String = RemoteApi.token): Response<Deep>

    companion object RemoteApi {
        const val token = "pk_5e8577fd11eb4469be629c5e2de8023f"
        val retrofit = Retrofit.Builder()
                .baseUrl("https://cloud.iexapis.com/stable/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StockServices::class.java)


    }
}
