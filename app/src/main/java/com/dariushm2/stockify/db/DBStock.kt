package com.dariushm2.stockify.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dariushm2.stockify.model.Symbol

@Database(entities = [Symbol::class], version = 1, exportSchema = false)
abstract class DBStock: RoomDatabase() {

    abstract fun getStockDao(): StockDao

}