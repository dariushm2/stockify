package com.dariushm2.stockify.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dariushm2.stockify.model.Deep
import com.dariushm2.stockify.model.Symbol
import com.dariushm2.stockify.remote.StockServices
import com.dariushm2.stockify.view.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MarketDepthViewModel : ViewModel() {


    val marketDepthLiveData = liveData {
        emitSource(getMarketDepth())

    }


    private suspend fun getMarketDepth():  LiveData<Deep> {
        val result = StockServices.retrofit.getDeep()
        if (result.isSuccessful)
            if (result.body() != null) {
                cacheSymbols(result.body()!!)
                println("$MY_APP: Fetched symbols from server")
            } else
                println("$MY_APP: Failed to fetch symbols from server")

    }

    private suspend fun cacheSymbols(symbols: List<Symbol>) = withContext(Dispatchers.IO) {
        symbols.forEach {
            MyApp.DB_STOCK_INSTANCE.getStockDao().insertSymbol(it)
        }
        println("$MY_APP: Cached symbols in DB")
    }
}

