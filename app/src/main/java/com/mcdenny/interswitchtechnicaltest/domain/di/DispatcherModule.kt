package com.mcdenny.interswitchtechnicaltest.domain.di

import com.mcdenny.interswitchtechnicaltest.domain.AppDispatcher
import com.mcdenny.interswitchtechnicaltest.domain.AppDispatcherImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DispatcherModule {

    @Binds
    @Singleton
    fun bindDispatcher(impl: AppDispatcherImpl): AppDispatcher
}