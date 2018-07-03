package com.dariushm2.stockify.view

import android.app.Application
import androidx.room.Room
import com.dariushm2.stockify.db.DBStock


class MyApp : Application() {

    val DB_STOCK = "DB_STOCK_INSTANCE"

    companion object {
        lateinit var DB_STOCK_INSTANCE: DBStock
    }

    override fun onCreate() {
        super.onCreate()
        DB_STOCK_INSTANCE = Room.databaseBuilder(this, DBStock::class.java, DB_STOCK)
                        //.allowMainThreadQueries()
                        .build()
        //Log.e("onCreate", "Invoked")
    }

}