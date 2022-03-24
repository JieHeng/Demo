package com.example.demo.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM CurrencyInfo")
    fun getAll(): List<CurrencyInfo>
}