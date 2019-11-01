package com.dariushm2.stockify.viewModel

import androidx.lifecycle.*
import com.dariushm2.stockify.model.Quote
import com.dariushm2.stockify.model.Watch
import com.dariushm2.stockify.remote.StockServices
import com.dariushm2.stockify.view.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException


class WatchListViewModel : ViewModel() {

    //val watchListLiveData = WatchListRepository().watchListLiveData

    val MY_APP = "Stockify"

    val quotesLiveData = liveData {

        emitSource(getCachedQuotes())

        val watchList = MyApp.DB_STOCK_INSTANCE.getStockDao().getWatches()
        getQuotes(watchList)

    }

    private fun getCachedQuotes(): LiveData<List<Quote>> {
        //delay(5000)
        val quotes = MyApp.DB_STOCK_INSTANCE.getStockDao().getQuoteList()
        //println("$MY_APP: Fetched quote from DB ${quotes.value?.size}")
        return quotes
    }

    private suspend fun cacheQuote(quote: Quote) {

        MyApp.DB_STOCK_INSTANCE.getStockDao().insertQuote(quote)

        //println("$MY_APP: Cached quote in DB")
    }

    private suspend fun getQuotes(watchList: List<Watch>) {

        while (true) {
            watchList.forEach {
                getQuote(it)
            }
            delay(300000)
        }
    }

    private suspend fun getQuote(watch: Watch) = withContext(Dispatchers.IO) {
        val response = StockServices.retrofit.getQuote(watch.symbol)
        if (response.isSuccessful) {
            println("$MY_APP: Fetched quote from server. ${response.body()}")
            if (response.body() != null)
                cacheQuote(response.body()!!)
        } else
            throw IOException()
    }
}