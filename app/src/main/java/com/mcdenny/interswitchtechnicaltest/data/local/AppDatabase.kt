package com.mcdenny.interswitchtechnicaltest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mcdenny.interswitchtechnicaltest.domain.model.ItemFee

@TypeConverters(Converters::class)
@Database(entities = [ItemFee::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun itemFeeDao(): ItemFeeDao
}