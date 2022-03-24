package com.example.demo.util

import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {
    val io: CoroutineContext
    val default: CoroutineContext
    val main: CoroutineContext
}