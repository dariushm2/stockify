package com.dariushm2.stockify.viewModel

import androidx.lifecycle.ViewModel
import com.dariushm2.stockify.repository.SymbolsRepository


class SymbolsViewModel : ViewModel() {

    val symbolsLiveData = SymbolsRepository().symbolsLiveData

    override fun onCleared() {
        //symbolsLiveData.cancel()
    }


    init {

    }

}