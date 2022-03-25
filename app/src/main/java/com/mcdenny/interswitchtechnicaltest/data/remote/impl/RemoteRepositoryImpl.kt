package com.mcdenny.interswitchtechnicaltest.data.remote.impl

import com.mcdenny.interswitchtechnicaltest.data.remote.ApiService
import com.mcdenny.interswitchtechnicaltest.data.remote.model.RemoteResponseEntity
import com.mcdenny.interswitchtechnicaltest.domain.model.ResponseEntity
import com.mcdenny.interswitchtechnicaltest.domain.repository.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val service: ApiService
): RemoteRepository {

    override suspend fun fetchTransaction(transactionId: Long): RemoteResponseEntity {
        return try {
            service.fetchTransaction(transactionId)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }
}