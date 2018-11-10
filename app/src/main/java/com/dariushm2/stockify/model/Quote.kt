package com.dariushm2.stockify.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(@PrimaryKey
                 val symbol: String,
                 val companyName: String,
                 val primaryExchange: String,
                 val sector: String,
                 val open: Float,
                 val close: Float,
                 val high: Float,
                 val low: Float,
                 val latestPrice: Float,
                 val previousClose: Float,
                 val change: Float,
                 val changePercent: Float)