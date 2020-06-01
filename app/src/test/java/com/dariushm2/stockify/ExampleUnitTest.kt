package com.dariushm2.stockify

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dariushm2.stockify.model.Quote
import com.dariushm2.stockify.model.Symbol
import com.dariushm2.stockify.viewModel.SymbolsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

//    @get:Rule
//    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = TestCoroutineScope()
    //@Mock
    val symbolsViewModel = SymbolsViewModel()
    @Mock
    lateinit var observer: Observer<List<Symbol>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val cities = mutableListOf("Toronto", "Winnepeg", "Vancouver")
    }



    @Test
    fun symbolViewModelTest() = coroutinesTestRule.runBlockingTest {

        symbolsViewModel.symbolsLiveData.observeForever(observer)
        verify(observer).onChanged(ArgumentMatchers.anyList())
    }
}
