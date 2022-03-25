package com.mcdenny.interswitchtechnicaltest.domain.repository

import com.mcdenny.interswitchtechnicaltest.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun insertTransaction(transaction: Transaction)

    suspend fun deleteTransaction(transaction: Transaction)

    suspend fun clearTransactions()

    fun findTransaction(transactionId: Long): Flow<List<Transaction>>
}