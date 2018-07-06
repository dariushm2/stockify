package com.dariushm2.stockify.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(@PrimaryKey
                 val symbol: String,
                 val companyName: String)