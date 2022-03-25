package com.mcdenny.interswitchtechnicaltest.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mcdenny.interswitchtechnicaltest.domain.AppDispatcher
import com.mcdenny.interswitchtechnicaltest.domain.model.Resource
import com.mcdenny.interswitchtechnicaltest.domain.usecases.ClearTransactionsUseCase
import com.mcdenny.interswitchtechnicaltest.domain.usecases.FetchRemoteTransactionUseCase
import com.mcdenny.interswitchtechnicaltest.domain.usecases.FindCachedTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchRemoteTransactionUseCase: FetchRemoteTransactionUseCase,
    private val findCachedTransactionUseCase: FindCachedTransactionUseCase,
    private val clearTransactionsUseCase: ClearTransactionsUseCase,
    private val dispatcher: AppDispatcher
) : ViewModel() {

    private val _transactionState = MutableLiveData<TransactionState>()
    val transactionState: LiveData<TransactionState> get() = _transactionState

    init {
        _transactionState.value = TransactionState()
    }

    fun searchTransaction(transactionId: String) {
        val id = transactionId.toLong()

        viewModelScope.launch {
            val foundTransaction = findCachedTransactionUseCase(id).first()

            if (foundTransaction.isEmpty()) {
                withContext(dispatcher.io) {
                    fetchRemoteTransactionUseCase(id).collect {
                        when(it) {
                            is Resource.Loading -> {
                                _transactionState.value = transactionState.value!!.copy(
                                    loading = true
                                )
                            }
                            is Resource.Success -> {
                                _transactionState.value = transactionState.value!!.copy(
                                    loading = false,
                                    transaction = it.data
                                )
                            }
                            is Resource.Error -> {
                                _transactionState.value = transactionState.value!!.copy(
                                    loading = false,
                                    failure = it.exception
                                )
                            }
                        }
                    }
                }
            } else {
                withContext(dispatcher.main) {
                    _transactionState.value = transactionState.value!!.copy(
                        loading = false,
                        transaction = foundTransaction[0]
                    )
                }
            }
        }
    }
}