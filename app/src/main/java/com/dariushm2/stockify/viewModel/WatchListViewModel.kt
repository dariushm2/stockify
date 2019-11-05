package com.dariushm2.stockify.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dariushm2.stockify.model.Quote
import com.dariushm2.stockify.model.Watch
import com.dariushm2.stockify.remote.StockServices
import com.dariushm2.stockify.view.MyApp
import kotlinx.coroutines.*
import java.io.IOException


class WatchListViewModel : ViewModel() {

    //val watchListLiveData = WatchListRepository().watchListLiveData

    val MY_APP = "Stockify"


    private lateinit var watchlist: List<Watch>

    private var isObserved = false

    private val scope = CoroutineScope(Dispatchers.IO)

    val quotesLiveData = liveData {

        emitSource(getCachedQuotes())

        MyApp.DB_STOCK_INSTANCE.getStockDao().getWatches().observeForever(Observer {
            watchlist = it
            if (!isObserved) {
                scope.launch {
                    getQuotes()
                }
            }
            isObserved = true
        })



    }

    private fun getCachedQuotes(): LiveData<List<Quote>> {
        //delay(5000)
        //MyApp.DB_STOCK_INSTANCE.getStockDao().deleteQuotes()
        val quotes = MyApp.DB_STOCK_INSTANCE.getStockDao().getQuoteList()
        //println("$MY_APP: Fetched quote from DB ${quotes.value?.size}")
        return quotes
    }

    private suspend fun cacheQuote(quote: Quote) {

        MyApp.DB_STOCK_INSTANCE.getStockDao().insertQuote(quote)

        //println("$MY_APP: Cached quote in DB")
    }

    private suspend fun getQuotes() {
        while (true) {
            watchlist.forEach {
                getQuote(it)
            }
            delay(5000)
            println("$MY_APP: another api call")
        }
    }

    private suspend fun getQuote(watch: Watch) = withContext(Dispatchers.IO) {
        val response = StockServices.retrofit.getQuote(watch.symbol)
        if (response.isSuccessful) {
            //println("$MY_APP: Fetched quote from server. ${response.body()}")
            if (response.body() != null)
                cacheQuote(response.body()!!)
        } else
            throw IOException()
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}