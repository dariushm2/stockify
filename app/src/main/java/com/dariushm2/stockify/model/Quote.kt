package com.dariushm2.stockify.model

import androidx.databinding.InverseMethod
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.lang.ClassCastException

@Entity
data class Quote(@PrimaryKey
                 @ColumnInfo(name = "symbol") var symbol: String,
                 @ColumnInfo(name = "companyName") var companyName: String,
                 @ColumnInfo(name = "primaryExchange") var primaryExchange: String,
                 private val _open: Float,
                 private val _close: Float,
                 private val _high: Float,
                 private val _low: Float,
                 private val _latestPrice: Float,
                 private val _previousClose: Float,
                 private val _change: Float,
                 private val _changePercent: Float) {
    @ColumnInfo(name = "open") var open = _open
        set(value) { field = String.format("%.02f", value).toFloat()}
    @ColumnInfo(name = "close") var close = _close
        set(value) { field = String.format("%.02f", value).toFloat()}
    @ColumnInfo(name = "high") var high = _high
        set(value) { field = String.format("%.02f", value).toFloat()}
    @ColumnInfo(name = "low") var low = _low
        set(value) { field = String.format("%.02f", value).toFloat()}
    @ColumnInfo(name = "latestPrice") var latestPrice = _latestPrice
        set(value) { field = String.format("%.02f", value).toFloat()}
    @ColumnInfo(name = "previousClose") var previousClose = _previousClose
        set(value) { field = String.format("%.02f", value).toFloat()}
    @ColumnInfo(name = "change") var change = _change
        set(value) { field = String.format("%.02f", value).toFloat()}
    @ColumnInfo(name = "changePercent") var changePercent = _changePercent
        set(value) { field = String.format("%.02f", value * 100).toFloat()}

    override fun toString(): String {
        return "Quote($symbol, $companyName, $primaryExchange, $open, $close, $high, $low, $latestPrice, $previousClose, $change, $changePercent)"
    }

}

object Converter {
    @InverseMethod("floatToString")
    fun floatToString(value: Float): String {
        var strValue: String = ""
        try {
            strValue = value.toString()
        } catch (e: ClassCastException) {
        }
        return strValue
    }
}