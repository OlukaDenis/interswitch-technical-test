package com.mcdenny.interswitchtechnicaltest.domain.usecases

import com.mcdenny.interswitchtechnicaltest.data.remote.resolveError
import com.mcdenny.interswitchtechnicaltest.domain.model.Resource
import com.mcdenny.interswitchtechnicaltest.domain.repository.LocalRepository
import com.mcdenny.interswitchtechnicaltest.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchRemoteTransactionUseCase @Inject constructor(
    private val local: LocalRepository,
    private val remote: RemoteRepository
) {

    suspend operator fun invoke(transactionId: Long) = flow {
        emit(Resource.Loading)

        try {
            val response = remote.fetchTransaction(transactionId)
            local.insertTransaction(response.response)
            emit(Resource.Success(response.response))
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable.resolveError()))
        }
    }
}