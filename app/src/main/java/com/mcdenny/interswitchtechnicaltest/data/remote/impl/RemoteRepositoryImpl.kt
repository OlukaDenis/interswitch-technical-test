package com.mcdenny.interswitchtechnicaltest.data.remote.impl

import com.mcdenny.interswitchtechnicaltest.data.remote.ApiService
import com.mcdenny.interswitchtechnicaltest.data.remote.model.RemoteResponseEntity
import com.mcdenny.interswitchtechnicaltest.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val service: ApiService
): RemoteRepository {

    override suspend fun fetchItemFee(itemFeeId: Long): RemoteResponseEntity {
        return try {
            service.fetchItemFee(itemFeeId)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }
}