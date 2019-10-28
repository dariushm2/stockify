package com.dariushm2.stockify.view.addSymbol

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dariushm2.stockify.databinding.SymbolItemBinding
import com.dariushm2.stockify.model.Symbol
import com.dariushm2.stockify.model.Watch
import com.dariushm2.stockify.view.MyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



/**
 * [RecyclerView.Adapter] that can display a [Symbol] and makes a call to the
 * specified [OnStocksListListener].
 * TODO: Replace the implementation with code for your data type.
 */
class SymbolsListAdapter(
        private var symbols: List<Symbol>)
    : RecyclerView.Adapter<SymbolsListAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var symbolsCached = symbols

    init {
        mOnClickListener = View.OnClickListener { v ->
            val symbol = v.tag as Symbol
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //.inflate(R.layout.symbol_item, parent, false)
        val symbolItemBinding: SymbolItemBinding = SymbolItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(symbolItemBinding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val symbol: Symbol = symbols[position]
        holder.bind(symbol)


        val watch = Watch(symbols[position].symbol)
        holder.itemView.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val result = MyApp.DB_STOCK_INSTANCE.getStockDao().insertWatch(watch)
                println("Stockify: $result")
                withContext(Dispatchers.Main) {
                    it.findNavController().popBackStack()
                }
            }
        }
    }

    override fun getItemCount(): Int = symbols.size

    fun addItems(symbols: List<Symbol>) {
        this.symbols = symbols
        this.symbolsCached = this.symbols
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val symbolsDataBinding: SymbolItemBinding) : RecyclerView.ViewHolder(symbolsDataBinding.root) {
        //val txtSymbol: TextView = mView.txtSymbol
        //val txtName: TextView = mView.txtName

        fun bind(symbol: Symbol) {
            symbolsDataBinding.symbol = symbol
            symbolsDataBinding.executePendingBindings()
        }

        fun getSymbolsDataBinding(): SymbolItemBinding {
            return symbolsDataBinding
        }

    }


    fun filter(text: String) {


        symbols = symbolsCached
        val newList = symbols.filter { symbol -> symbol.symbol.startsWith(text, true) }
        symbols = newList

        notifyDataSetChanged()

    }
}
