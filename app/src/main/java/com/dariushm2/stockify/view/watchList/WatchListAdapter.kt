package com.dariushm2.stockify.view.watchList


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.dariushm2.stockify.R
import com.dariushm2.stockify.databinding.QuoteItemBinding
import com.dariushm2.stockify.model.Quote
import com.dariushm2.stockify.model.Watch
import com.dariushm2.stockify.view.MyApp

class WatchListAdapter(private var quotes: List<Quote>) : RecyclerView.Adapter<WatchListAdapter.ViewHolder>() {


    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val quoteItemBinding: QuoteItemBinding = QuoteItemBinding.inflate(layoutInflater, parent, false)
        context = parent.context
        return ViewHolder(quoteItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = quotes[position]
        holder.bind(quote)

        holder.itemView.setOnLongClickListener {
            deleteWatch(position)
        }
    }

    private fun deleteWatch(position: Int): Boolean {
        val dialog = AlertDialog.Builder(context)
                .setTitle(quotes[position].symbol)
                .setMessage(R.string.deleteWatchlist)
                .setPositiveButton(android.R.string.yes, { dialog, id ->
                    val watch = Watch(quotes[position].symbol)
                    val result = MyApp.DB_STOCK_INSTANCE.getStockDao().deleteWatch(watch)
                    MyApp.DB_STOCK_INSTANCE.getStockDao().deleteQuote(quotes[position])
                    println("Stockify: onPositiveButton Clicked ${watch.symbol} = $result")
                })
                .create()
        dialog.show()
        return true
    }


    override fun getItemCount(): Int = quotes.size

    fun addItems(quotes: List<Quote>) {
        this.quotes = quotes
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
