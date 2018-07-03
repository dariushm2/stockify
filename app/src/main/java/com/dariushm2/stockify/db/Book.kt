package com.dariushm2.stockify.db

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Book(@PrimaryKey(autoGenerate = true)
           var id: Long,
           var title: String,
           var author: String,
           var description: String) {
    @Ignore
    constructor(title: String, author: String, description: String): this(0, title, author, description)

}
