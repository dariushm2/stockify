package com.dariushm2.stockify.remote

object RemoteApiUtils {
    //val BASE_URL = "https://api.iextrading.com/1.0"

    val symbolsService: SymbolsService
        get() = RetrofitClient.getClient().create(SymbolsService::class.java)
}
