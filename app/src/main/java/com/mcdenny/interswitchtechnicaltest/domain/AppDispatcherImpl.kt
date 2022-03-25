package com.mcdenny.interswitchtechnicaltest.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppDispatcherImpl @Inject constructor() : AppDispatcher {
    override val io: CoroutineDispatcher get() = Dispatchers.IO
    override val main: CoroutineDispatcher get() = Dispatchers.Main
}