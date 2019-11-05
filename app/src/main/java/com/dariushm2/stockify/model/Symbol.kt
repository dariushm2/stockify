package com.dariushm2.stockify.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Symbol(@PrimaryKey
                  var symbol: String,
                  var name: String,
                  var isEnabled: Boolean,
                  var type: String,
                  var iexId: String)
