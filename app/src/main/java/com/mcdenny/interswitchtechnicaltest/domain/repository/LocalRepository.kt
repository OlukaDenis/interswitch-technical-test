package com.mcdenny.interswitchtechnicaltest.domain.repository

import com.mcdenny.interswitchtechnicaltest.domain.model.ItemFee
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun insertItemFee(itemFee: ItemFee)

    suspend fun deleteItemFee(itemFee: ItemFee)

    suspend fun clearItemFees()

    fun findItemFee(itemFeeId: Long): Flow<List<ItemFee>>
}