package com.mcdenny.interswitchtechnicaltest.domain

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatcher {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}