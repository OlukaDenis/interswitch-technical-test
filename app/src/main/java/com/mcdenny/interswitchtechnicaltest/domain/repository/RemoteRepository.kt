package com.mcdenny.interswitchtechnicaltest.domain.repository

import com.mcdenny.interswitchtechnicaltest.domain.model.ResponseEntity

interface RemoteRepository {

    suspend fun fetchTransaction(transactionId: Long): ResponseEntity
}