package com.dariushm2.stockify.view.watchList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dariushm2.stockify.R
import com.dariushm2.stockify.viewModel.WatchListViewModel
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*


class WatchListFragment : Fragment() {


    private lateinit var adapter: WatchListAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_watch_list, container, false)

        activity?.findViewById<SearchView>(R.id.searchView)?.visibility = View.VISIBLE
        activity?.findViewById<SearchView>(R.id.searchView)?.clearFocus()
        activity?.findViewById<SearchView>(R.id.searchView)?.setQuery("", true)
        //Log.e("Fragment", "Watch List")



        val swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.srlWatchList)
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(context!!, R.color.colorAccent))
        swipeRefreshLayout.isEnabled = true
        swipeRefreshLayout.isRefreshing = true

        val recyclerView: RecyclerView = view.findViewById(R.id.rcvWatchList)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val watchListViewModel = ViewModelProvider(this).get(WatchListViewModel::class.java)


        adapter = WatchListAdapter(listOf())
        recyclerView.adapter = adapter

        watchListViewModel.quotesLiveData.observe(this, Observer {
            val result = it.sortedBy { it.symbol }
            swipeRefreshLayout.isRefreshing = false
            swipeRefreshLayout.isEnabled = false
            //println("Stockify: quotes are observed")
            adapter.addItems(result)
        })


        return view
    }

}
