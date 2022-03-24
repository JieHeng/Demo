package com.example.demo.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [CurrencyInfo::class]
)
abstract class CurrencyDatabase: RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        const val NAME = "CurrencyDatabase.db"
    }
}