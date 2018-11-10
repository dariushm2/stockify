package com.dariushm2.stockify.viewModel

import androidx.lifecycle.ViewModel
import com.dariushm2.stockify.repository.SymbolsRepository
import com.dariushm2.stockify.repository.WatchListRepository


class WatchListViewModel : ViewModel() {

    val watchListLiveData = WatchListRepository().watchListLiveData

    override fun onCleared() {
        //symbolsLiveData.cancel()
    }


    init {

    }

}