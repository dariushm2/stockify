package com.dariushm2.stockify.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dariushm2.stockify.model.Quote
import com.dariushm2.stockify.model.Symbol
import com.dariushm2.stockify.model.Watch

@Dao
interface StockDao {


    /***
     * CRUD functions for Symbol entity
     */

    @Query("SELECT * FROM Symbol WHERE symbol = :symbol;")
    fun getSymbol(symbol: String): Symbol

    @Query("SELECT * FROM Symbol;")
    fun getSymbols(): LiveData<List<Symbol>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSymbol(symbol: Symbol): Long

    @Update
    fun updateSymbol(symbol: Symbol)

    @Delete
    fun deleteSymbol(symbol: Symbol)

    @Query("DELETE FROM Symbol WHERE 1")
    fun deleteSymbols()


    /***
     * CRUD functions for Watch entity
     */

    @Query("SELECT * FROM Watch;")
    suspend fun getWatches(): List<Watch>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWatch(watch: Watch): Long

    @Delete
    fun deleteWatch(watch: Watch)

    @Query("DELETE FROM Watch WHERE 1")
    fun deleteWatchList()

    @Query("SELECT COUNT(symbol) FROM Watch")
    fun getRowCount(): Int


    /***
     * CRUD functions for Quote entity
     */

    @Query("SELECT * FROM Quote;")
    fun getQuoteList(): LiveData<List<Quote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote): Long

    @Delete
    fun deleteQuote(quote: Quote)

    @Query("DELETE FROM Quote WHERE 1")
    fun deleteQuoteList()

}