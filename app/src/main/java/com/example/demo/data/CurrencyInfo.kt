package com.example.demo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyInfo(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String
)