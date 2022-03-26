package com.mcdenny.interswitchtechnicaltest.data.local.di

import android.content.Context
import androidx.room.Room
import com.mcdenny.interswitchtechnicaltest.data.Constants.DATABASE_NAME
import com.mcdenny.interswitchtechnicaltest.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideItemFeeDao(database: AppDatabase) = database.itemFeeDao()
}