package com.dariushm2.stockify.view.addSymbol

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.dariushm2.stockify.R
import com.dariushm2.stockify.databinding.SymbolItemBinding

import com.dariushm2.stockify.model.Symbol
import com.dariushm2.stockify.view.MyApp

/**
 * [RecyclerView.Adapter] that can display a [Symbol] and makes a call to the
 * specified [OnStocksListListener].
 * TODO: Replace the implementation with code for your data type.
 */
class SymbolsListAdapter(
        private var symbols: List<Symbol>)
    : RecyclerView.Adapter<SymbolsListAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var symbolsCached: MutableList<Symbol> = mutableListOf()
    private var symbolsFiltered: MutableList<Symbol> = mutableListOf()
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
        val symbol:Symbol = symbols[position]
        holder.bind(symbol)


        val bundle = Bundle()
        val tag = holder.itemView.context.getString(R.string.argSymbol)
        bundle.putString(tag, symbols[position].symbol)
        holder.itemView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.actionAddSymbolToWatchList, bundle))

        //Log.e("OnClick", "Invoked")

    }

    override fun getItemCount(): Int = symbols.size

    fun addItems(symbols: List<Symbol>) {
        this.symbols = symbols
        this.symbolsCached.addAll(symbols)
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
        Log.e("filter", "invoked")
        if (text.isEmpty()) {
            symbols = symbolsCached
        }
        else {
            symbolsFiltered = mutableListOf()
            symbolsCached.forEach {
                if (it.symbol.startsWith(text, true))
                    symbolsFiltered.add(it)
            }
            symbols = symbolsFiltered
        }
        notifyDataSetChanged()
    }
}
