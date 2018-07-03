package com.dariushm2.stockify.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

object RetrofitClient {
    private const val baseUrl = "https://api.iextrading.com/1.0/"
    private val retrofit: Retrofit
    init {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                //.callbackExecutor(Executors.newSingleThreadExecutor())
                .build()
    }


    fun getClient(): Retrofit {
        return retrofit
    }

}
