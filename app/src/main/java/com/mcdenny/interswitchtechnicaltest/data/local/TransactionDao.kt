package com.mcdenny.interswitchtechnicaltest.data.local

import androidx.room.*
import com.mcdenny.interswitchtechnicaltest.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE id == :transactionId")
    fun findTransaction(transactionId: Long): Flow<List<Transaction>>

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Query("DELETE FROM transactions")
    suspend fun clearTransactions()
}