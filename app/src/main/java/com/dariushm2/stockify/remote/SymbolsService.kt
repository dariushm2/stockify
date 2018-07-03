package com.dariushm2.stockify.remote


import com.dariushm2.stockify.model.Symbol
import retrofit2.Call
import retrofit2.http.GET

interface SymbolsService {
    @GET("ref-data/symbols")
    fun getSymbols(): Call<List<Symbol>>
}
