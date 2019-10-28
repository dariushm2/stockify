package com.dariushm2.stockify.model

import androidx.databinding.InverseMethod
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.ClassCastException

@Entity
data class Quote(@PrimaryKey
                 var symbol: String,
                 var companyName: String,
                 var primaryExchange: String,
                 var open: Float,
                 var close: Float,
                 var high: Float,
                 var low: Float,
                 var latestPrice: Float,
                 var previousClose: Float,
                 var change: Float,
                 var changePercent: Float)

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