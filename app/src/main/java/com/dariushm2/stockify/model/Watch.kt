package com.dariushm2.stockify.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Watch(@PrimaryKey(autoGenerate = false)
                     val symbol: String)