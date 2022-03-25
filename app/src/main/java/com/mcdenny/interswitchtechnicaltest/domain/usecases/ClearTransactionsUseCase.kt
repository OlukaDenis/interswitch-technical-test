package com.mcdenny.interswitchtechnicaltest.domain.usecases

import com.mcdenny.interswitchtechnicaltest.domain.repository.LocalRepository
import javax.inject.Inject

class ClearTransactionsUseCase @Inject constructor(
    private val repository: LocalRepository
) {

    suspend operator fun invoke() {
        repository.clearTransactions()
    }
}