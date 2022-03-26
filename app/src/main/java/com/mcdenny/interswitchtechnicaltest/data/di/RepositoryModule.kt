package com.mcdenny.interswitchtechnicaltest.data.di

import com.mcdenny.interswitchtechnicaltest.data.local.impl.LocalRepositoryImpl
import com.mcdenny.interswitchtechnicaltest.data.remote.impl.RemoteRepositoryImpl
import com.mcdenny.interswitchtechnicaltest.domain.repository.LocalRepository
import com.mcdenny.interswitchtechnicaltest.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindRepository(impl: RemoteRepositoryImpl): RemoteRepository

    @Singleton
    @Binds
    fun bindLocalRepository(impl: LocalRepositoryImpl): LocalRepository

}