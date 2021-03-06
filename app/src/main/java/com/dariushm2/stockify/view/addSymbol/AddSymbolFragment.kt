package com.dariushm2.stockify.view.addSymbol


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dariushm2.stockify.R
import com.dariushm2.stockify.viewModel.SymbolsViewModel


class AddSymbolFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var adapter: SymbolsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.fragment_add_symbol, container, false)


        val searchView = activity?.findViewById<SearchView>(R.id.searchView)
        //searchView?.inputType = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
        searchView?.visibility = View.VISIBLE

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                adapter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter(newText)

                return true
            }
        })



        Log.e("Fragment", "Add Symbol")

        val swipeRefreshLayout: SwipeRefreshLayout = mView.findViewById(R.id.srlSymbols)
        val recyclerView: RecyclerView = mView.findViewById(R.id.rcvSymbols)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val symbolViewModel = ViewModelProvider(this).get(SymbolsViewModel::class.java)



        adapter = SymbolsListAdapter(listOf())
        recyclerView.adapter = adapter
        //Log.e("Fragment", "setAdapter")

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(context!!, R.color.colorAccent))
        swipeRefreshLayout.isEnabled = true
        swipeRefreshLayout.isRefreshing = true


        symbolViewModel.symbolsLiveData.observe(this, Observer {
            //Log.e("Symbols: ", "Observed")
            swipeRefreshLayout.isRefreshing = false
            swipeRefreshLayout.isEnabled = false
            adapter.addItems(it!!)
        })

        return mView

    }
}
