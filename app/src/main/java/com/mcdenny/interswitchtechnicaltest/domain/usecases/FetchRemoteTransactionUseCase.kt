package com.mcdenny.interswitchtechnicaltest.domain.usecases

import com.mcdenny.interswitchtechnicaltest.data.remote.mappers.RemoteTransactionMapper
import com.mcdenny.interswitchtechnicaltest.data.remote.resolveError
import com.mcdenny.interswitchtechnicaltest.domain.model.Resource
import com.mcdenny.interswitchtechnicaltest.domain.repository.LocalRepository
import com.mcdenny.interswitchtechnicaltest.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class FetchRemoteTransactionUseCase @Inject constructor(
    private val local: LocalRepository,
    private val remote: RemoteRepository,
    private val remoteTransactionMapper: RemoteTransactionMapper
) {

    suspend operator fun invoke(transactionId: Long) = flow {
        emit(Resource.Loading)

        try {
            val (response, _, errorMessage) = remote.fetchTransaction(transactionId)
            if (response != null) {
                val transaction = remoteTransactionMapper.mapToDomain(response)
                local.insertTransaction(transaction)
                emit(Resource.Success(transaction))
            } else {
                emit(Resource.Error(errorMessage.toString()))
            }
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable.resolveError()))
        }
    }
}