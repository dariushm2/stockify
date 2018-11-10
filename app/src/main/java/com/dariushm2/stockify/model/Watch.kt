package com.dariushm2.stockify.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Watch(@PrimaryKey
                     val symbol: String)