package com.dariushm2.stockify.view.watchList

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dariushm2.stockify.R
import com.dariushm2.stockify.model.Quote
import com.dariushm2.stockify.model.Watch
import com.dariushm2.stockify.view.MyApp
import com.dariushm2.stockify.view.addSymbol.SymbolsListAdapter
import com.dariushm2.stockify.viewModel.SymbolsViewModel
import com.dariushm2.stockify.viewModel.WatchListViewModel

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [WatchListFragment.OnWatchListInteractionListener] interface.
 */
class WatchListFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private var watchList: List<Watch> = listOf()
    private lateinit var adapter: WatchListAdapter

    private var listenerWatch: OnWatchListInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_watch_list, container, false)

        activity?.findViewById<SearchView>(R.id.searchView)?.visibility = View.GONE
        Log.e("Fragment", "Watch List")

        val symbol = arguments?.getString(context?.getString(R.string.argSymbol))
        if (symbol != null) {
            val watch = Watch(symbol)
            MyApp.DB_STOCK_INSTANCE.getStockDao().insertWatch(watch)
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.rcvWatchList)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val watchListViewModel = ViewModelProviders.of(this).get(WatchListViewModel::class.java)


        adapter = WatchListAdapter(listOf())
        recyclerView.adapter = adapter

        watchListViewModel.watchListLiveData.observe(this, Observer {
            //adapter.addItems(it!!)
        })


        watchList = MyApp.DB_STOCK_INSTANCE.getStockDao().getWatchList()
        Log.e("WatchList", watchList.size.toString())
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnWatchListInteractionListener) {
            listenerWatch = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnWatchListInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerWatch = null
    }



    interface OnWatchListInteractionListener {
        // TODO: Update argument type and name
        fun onWatchListInteraction(item: Quote?)
    }

    fun navigateToAddSymbol() {
        //Navigation.createNavigateOnClickListener(R.id.actionWatchListToAddSymbol)
        view?.findNavController()?.navigate(R.id.addSymbolFragment)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                WatchListFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
