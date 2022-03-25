/*
 * Copyright (c) 2022   Denis Oluka
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

package com.mcdenny.interswitchtechnicaltest.presentation.ui

import androidx.lifecycle.*
import com.mcdenny.interswitchtechnicaltest.domain.AppDispatcher
import com.mcdenny.interswitchtechnicaltest.domain.model.Resource
import com.mcdenny.interswitchtechnicaltest.domain.usecases.ClearTransactionsUseCase
import com.mcdenny.interswitchtechnicaltest.domain.usecases.FetchRemoteTransactionUseCase
import com.mcdenny.interswitchtechnicaltest.domain.usecases.FindCachedTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchRemoteTransactionUseCase: FetchRemoteTransactionUseCase,
    private val findCachedTransactionUseCase: FindCachedTransactionUseCase,
    private val clearTransactionsUseCase: ClearTransactionsUseCase,
    private val dispatcher: AppDispatcher
) : ViewModel() {

    private val _transactionState = MutableStateFlow<TransactionState>(TransactionState.Initial)
    val transactionState get() = _transactionState.asLiveData()

//    init {
//        _transactionState.value = TransactionState()
//    }

    fun searchTransaction(transactionId: String) {
        val id = transactionId.toLong()

        viewModelScope.launch {
            val foundTransaction = findCachedTransactionUseCase(id).first()

            if (foundTransaction.isEmpty()) {
                withContext(dispatcher.io) {
                    fetchRemoteTransactionUseCase(id).collect {
                        when (it) {
                            is Resource.Loading -> {
                                _transactionState.value = TransactionState.Loading
                            }
                            is Resource.Success -> {
                                _transactionState.value = TransactionState.Success(it.data)
                            }
                            is Resource.Error -> {
                                _transactionState.value = TransactionState.Error(it.exception)
                            }
                        }
                    }
                }
            } else {
                _transactionState.value = TransactionState.Success(foundTransaction[0])
            }
        }
    }
}