package com.mcdenny.interswitchtechnicaltest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mcdenny.interswitchtechnicaltest.domain.model.Transaction

@TypeConverters(Converters::class)
@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}