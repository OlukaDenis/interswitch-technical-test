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

package com.mcdenny.interswitchtechnicaltest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mcdenny.domain.AppDispatcher
import com.mcdenny.domain.model.Resource
import com.mcdenny.domain.usecases.ClearItemFeesUseCase
import com.mcdenny.domain.usecases.FetchRemoteItemFeeUseCase
import com.mcdenny.domain.usecases.FindCachedItemFeeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchRemoteItemFeeUseCase: FetchRemoteItemFeeUseCase,
    private val findCachedItemFeeUseCase: FindCachedItemFeeUseCase,
    private val clearItemFeesUseCase: ClearItemFeesUseCase,
    private val dispatcher: AppDispatcher
) : ViewModel() {

    private val _itemFeeState = MutableStateFlow<ItemFeeState>(ItemFeeState.Initial)
    val itemFeeState get() = _itemFeeState.asLiveData()

    fun searchItemFee(itemFeeId: String) {
        val id = itemFeeId.toLong()

        viewModelScope.launch {
            val foundTransaction = findCachedItemFeeUseCase(id).first()

            if (foundTransaction.isEmpty()) {
                withContext(dispatcher.io) {
                    fetchRemoteItemFeeUseCase(id).collect {
                        when (it) {
                            is Resource.Loading -> {
                                _itemFeeState.value = ItemFeeState.Loading
                            }
                            is Resource.Success -> {
                                _itemFeeState.value = ItemFeeState.Success(it.data)
                            }
                            is Resource.Error -> {
                                _itemFeeState.value = ItemFeeState.Error(it.exception)
                            }
                        }
                    }
                }
            } else {
                _itemFeeState.value = ItemFeeState.Success(foundTransaction[0])
            }
        }
    }

    fun clearItems() {
        viewModelScope.launch {
            clearItemFeesUseCase()
        }
    }
}