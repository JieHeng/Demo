package com.example.demo.di

import android.content.Context
import androidx.room.Room
import com.example.demo.data.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideCurrencyDatabase(@ApplicationContext context: Context): CurrencyDatabase {
        return Room.databaseBuilder(
            context,
            CurrencyDatabase::class.java, CurrencyDatabase.NAME
        )
            .createFromAsset(CurrencyDatabase.NAME)
            .build()
    }
}