package com.dariushm2.stockify.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dariushm2.stockify.model.Symbol

@Dao
interface StockDao {


    @Query("SELECT * FROM Symbol WHERE symbol = :symbol;")
    fun getSymbol(symbol: String): Symbol

    @Query("SELECT * FROM Symbol;")
    fun getSymbols(): LiveData<List<Symbol>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSymbol(symbol: Symbol): Long

    @Update
    fun updateSymbol(symbol: Symbol)

    @Delete
    fun deleteSymbol(symbol: Symbol)

    @Query("DELETE FROM Symbol WHERE 1")
    fun deleteSymbols()

}