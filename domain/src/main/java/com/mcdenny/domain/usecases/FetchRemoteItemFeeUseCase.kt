/*
 * Copyright (c) 2022 Denis Oluka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.mcdenny.domain.usecases

import com.mcdenny.domain.model.Resource
import com.mcdenny.domain.repository.LocalRepository
import com.mcdenny.domain.repository.RemoteRepository
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