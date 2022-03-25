package com.mcdenny.interswitchtechnicaltest.domain.usecases

import com.mcdenny.interswitchtechnicaltest.domain.repository.LocalRepository
import javax.inject.Inject

class FindCachedTransactionUseCase @Inject constructor(
    private val repository: LocalRepository
) {

    operator fun invoke(transactionId: Long) = repository.findTransaction(transactionId)
}