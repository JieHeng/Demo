package com.example.demo.di

import com.example.demo.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Singleton
    fun providesDispatchProvider(): DispatcherProvider {
        return object : DispatcherProvider {
            override val io: CoroutineContext
                get() = Dispatchers.IO
            override val default: CoroutineContext
                get() = Dispatchers.Default
            override val main: CoroutineContext
                get() = Dispatchers.Main
        }
    }
}