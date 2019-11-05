package com.dariushm2.stockify.view.misc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.dariushm2.stockify.R

class Disclaimer: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_disclaimer, container, false)


        activity?.findViewById<SearchView>(R.id.searchView)?.visibility = View.GONE
        //activity?.findViewById<SearchView>(R.id.searchView)?.queryHint = getString(R.string.app_name)
        return view
    }
}