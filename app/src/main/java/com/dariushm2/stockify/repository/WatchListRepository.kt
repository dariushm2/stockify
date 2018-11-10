package com.dariushm2.stockify.repository

import android.os.AsyncTask
import android.util.Log
import com.dariushm2.stockify.model.Quote
import com.dariushm2.stockify.view.MyApp
import com.dariushm2.stockify.model.Symbol
import com.dariushm2.stockify.remote.RemoteApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WatchListRepository {
    val watchListLiveData = MyApp.DB_STOCK_INSTANCE.getStockDao().getSymbols()

    init {
        getQuotes()
    }

    private fun getQuotes() {
        RemoteApiUtils.quoteService.getQuote("goog")
                .enqueue(object : Callback<Quote> {
                    override fun onResponse(call: Call<Quote>, response: Response<Quote>) {
                        if (response.isSuccessful) {

                            //cacheSymbols(response.body()!!)

                        } else {

                        }
                    }
                    override fun onFailure(call: Call<Quote>, t: Throwable) {
                        Log.e("failed", t.toString())
                    }
                })
    }

    class InsertAsyncTask: AsyncTask<List<Quote>, Unit, Unit>() {
        override fun doInBackground(vararg quotes: List<Quote>?) {
            quotes[0]?.forEach {
                //MyApp.DB_STOCK_INSTANCE.getStockDao().insertSymbol(it)
            }
            return
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            Log.e("symbols", "cached")
        }
    }

    private fun cacheSymbols(quotes: List<Quote>) {
        InsertAsyncTask().execute(quotes)
    }


}

