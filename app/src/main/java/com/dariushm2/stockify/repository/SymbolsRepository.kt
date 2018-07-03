package com.dariushm2.stockify.repository

import android.os.AsyncTask
import android.util.Log
import com.dariushm2.stockify.view.MyApp
import com.dariushm2.stockify.model.Symbol
import com.dariushm2.stockify.remote.RemoteApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SymbolsRepository {
    val symbolsLiveData = MyApp.DB_STOCK_INSTANCE.getStockDao().getSymbols()

    init {
        getSymbols()
    }

    private fun getSymbols() {
        RemoteApiUtils.symbolsService.getSymbols()
                .enqueue(object : Callback<List<Symbol>> {
                    override fun onResponse(call: Call<List<Symbol>>, response: Response<List<Symbol>>) {
                        if (response.isSuccessful) {

                            cacheSymbols(response.body()!!)

                        } else {

                        }
                    }
                    override fun onFailure(call: Call<List<Symbol>>, t: Throwable) {
                        Log.e("failed", t.toString())
                    }
                })
    }

    class InsertAsyncTask: AsyncTask<List<Symbol>, Unit, Unit>() {
        override fun doInBackground(vararg symbols: List<Symbol>?) {
            symbols[0]?.forEach { MyApp.DB_STOCK_INSTANCE.getStockDao().insertSymbol(it) }
            return
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            Log.e("symbols", "cached")
        }
    }

    private fun cacheSymbols(symbols: List<Symbol>) {
        InsertAsyncTask().execute(symbols)
    }


}

