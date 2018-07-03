package com.dariushm2.stockify.view.addSymbol

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dariushm2.stockify.databinding.SymbolItemBinding

import com.dariushm2.stockify.model.Symbol

/**
 * [RecyclerView.Adapter] that can display a [Symbol] and makes a call to the
 * specified [OnStocksListListener].
 * TODO: Replace the implementation with code for your data type.
 */
class SymbolsListAdapter(
        private var symbols: List<Symbol>)
    : RecyclerView.Adapter<SymbolsListAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

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

    }

    override fun getItemCount(): Int = symbols.size

    fun addItems(symbols: List<Symbol>) {
        this.symbols = symbols
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
}
