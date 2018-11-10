package com.dariushm2.stockify.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dariushm2.stockify.model.Symbol
import com.dariushm2.stockify.model.Watch

@Database(entities = [Symbol::class, Watch::class], version = 1, exportSchema = false)
abstract class DBStock: RoomDatabase() {

    abstract fun getStockDao(): StockDao

}