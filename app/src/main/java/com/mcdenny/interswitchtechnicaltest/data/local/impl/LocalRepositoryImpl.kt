package com.mcdenny.interswitchtechnicaltest.data.local.impl

import com.mcdenny.interswitchtechnicaltest.data.local.TransactionDao
import com.mcdenny.interswitchtechnicaltest.domain.model.Transaction
import com.mcdenny.interswitchtechnicaltest.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : LocalRepository {
    override suspend fun insertTransaction(transaction: Transaction) {
       transactionDao.insertTransaction(transaction)
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction)
    }

    override suspend fun clearTransactions() {
        transactionDao.clearTransactions()
    }

    override fun findTransaction(transactionId: Long): Flow<List<Transaction>> {
       return transactionDao.findTransaction(transactionId)
    }
}