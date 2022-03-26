package com.mcdenny.interswitchtechnicaltest.data.local

import androidx.room.*
import com.mcdenny.interswitchtechnicaltest.domain.model.ItemFee
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemFeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemFee(itemFee: ItemFee)

    @Query("SELECT * FROM item_fees")
    fun getAllItemFees(): Flow<List<ItemFee>>

    @Query("SELECT * FROM item_fees WHERE id == :itemFeeId")
    fun findItemFee(itemFeeId: Long): Flow<List<ItemFee>>

    @Delete
    suspend fun deleteItemFee(itemFee: ItemFee)

    @Query("DELETE FROM item_fees")
    suspend fun clearItemFees()
}