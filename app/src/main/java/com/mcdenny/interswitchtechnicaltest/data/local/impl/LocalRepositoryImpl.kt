package com.mcdenny.interswitchtechnicaltest.data.local.impl

import com.mcdenny.interswitchtechnicaltest.data.local.ItemFeeDao
import com.mcdenny.interswitchtechnicaltest.domain.model.ItemFee
import com.mcdenny.interswitchtechnicaltest.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val itemFeeDao: ItemFeeDao
) : LocalRepository {
    override suspend fun insertItemFee(itemFee: ItemFee) {
       itemFeeDao.insertItemFee(itemFee)
    }

    override suspend fun deleteItemFee(itemFee: ItemFee) {
        itemFeeDao.deleteItemFee(itemFee)
    }

    override suspend fun clearItemFees() {
        itemFeeDao.clearItemFees()
    }

    override fun findItemFee(itemFeeId: Long): Flow<List<ItemFee>> {
       return itemFeeDao.findItemFee(itemFeeId)
    }
}