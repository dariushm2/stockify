package com.dariushm2.stockify.view.watchList

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dariushm2.stockify.R
import com.dariushm2.stockify.model.Quote


import com.dariushm2.stockify.view.watchList.WatchListFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.quote_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [Quote] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class WatchListAdapter(
        private val mValues: List<Quote>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<WatchListAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Quote
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.quote_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.symbol
        holder.mContentView.text = item.companyName

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.txtSymbol
        val mContentView: TextView = mView.txtCompanyName

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
