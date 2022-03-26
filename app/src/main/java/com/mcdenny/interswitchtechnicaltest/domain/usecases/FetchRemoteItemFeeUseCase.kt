package com.mcdenny.interswitchtechnicaltest.domain.usecases

import com.mcdenny.interswitchtechnicaltest.data.remote.mappers.RemoteItemFeeMapper
import com.mcdenny.interswitchtechnicaltest.data.remote.resolveError
import com.mcdenny.interswitchtechnicaltest.domain.model.Resource
import com.mcdenny.interswitchtechnicaltest.domain.repository.LocalRepository
import com.mcdenny.interswitchtechnicaltest.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchRemoteItemFeeUseCase @Inject constructor(
    private val local: LocalRepository,
    private val remote: RemoteRepository,
    private val remoteItemFeeMapper: RemoteItemFeeMapper
) {

    suspend operator fun invoke(transactionId: Long) = flow {
        emit(Resource.Loading)

        try {
            val (response, _, errorMessage) = remote.fetchItemFee(transactionId)
            if (response != null) {
                val transaction = remoteItemFeeMapper.mapToDomain(response)
                local.insertItemFee(transaction)
                emit(Resource.Success(transaction))
            } else {
                emit(Resource.Error(errorMessage.toString()))
            }
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable.resolveError()))
        }
    }
}