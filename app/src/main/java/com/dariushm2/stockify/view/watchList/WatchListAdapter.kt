package com.dariushm2.stockify.view.watchList


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dariushm2.stockify.databinding.QuoteItemBinding
import com.dariushm2.stockify.model.Quote
import com.dariushm2.stockify.view.watchList.WatchListFragment.OnWatchListInteractionListener

/**
 * [RecyclerView.Adapter] that can display a [Quote] and makes a call to the
 * specified [OnWatchListInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class WatchListAdapter(
        private var quotes: List<Quote>
        //, private val mListenerWatch: OnWatchListInteractionListener?
) : RecyclerView.Adapter<WatchListAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Quote
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            //mListenerWatch?.onWatchListInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val quoteItemBinding: QuoteItemBinding = QuoteItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(quoteItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = quotes[position]
        holder.bind(quote)

        with(holder.itemView) {
            tag = quote
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = quotes.size

    fun addItems(quotes: List<Quote>) {
        this.quotes = quotes
        //this.symbolsCached.addAll(symbols)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val quotesDataBinding: QuoteItemBinding) : RecyclerView.ViewHolder(quotesDataBinding.root) {
        fun bind(quote: Quote) {
            quotesDataBinding.quote = quote
            quotesDataBinding.executePendingBindings()
        }

        fun getSymbolsDataBinding(): QuoteItemBinding {
            return quotesDataBinding
        }
    }
}
