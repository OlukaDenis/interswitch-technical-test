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

package com.mcdenny.interswitchtechnicaltest.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.mcdenny.interswitchtechnicaltest.data.remote.mappers.RemoteItemFeeMapper
import com.mcdenny.interswitchtechnicaltest.data.remote.model.RemoteItemFee
import com.mcdenny.interswitchtechnicaltest.data.remote.model.RemoteResponseEntity
import com.mcdenny.interswitchtechnicaltest.domain.model.Resource
import com.mcdenny.interswitchtechnicaltest.domain.repository.LocalRepository
import com.mcdenny.interswitchtechnicaltest.domain.repository.RemoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchRemoteItemFeeUseCaseTest {
    private lateinit var fetchRemoteItemFeeUseCase: FetchRemoteItemFeeUseCase

    @MockK
    lateinit var localRepository: LocalRepository

    @MockK
    lateinit var remoteRepository: RemoteRepository

    @MockK
    lateinit var remoteItemMapper: RemoteItemFeeMapper

    private val itemFee = RemoteItemFee(
        id = 30122,
        excise = 0.0,
        exciseTaxAccount = "",
        feeGroups = emptyList(),
        hasProviderServiceCharge = false,
        isActive = false,
        isInclusiveInAmount = false,
        issueDate = "",
        name = "Test",
        overrideBillerFee = false,
        payConfiguration = emptyList(),
        providerServiceCharge = 0.0,
        providerServiceChargeAccount = "",
        taxAccount = "",
        vat = 0.0,
        withholdingTax = 0.0,
        withholdingTaxAccount = "",
    )

    private val response = RemoteResponseEntity(
        response = itemFee,
        responseCode = 90999,
        responseMessage = null
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        fetchRemoteItemFeeUseCase =
            FetchRemoteItemFeeUseCase(localRepository, remoteRepository, remoteItemMapper)
    }

    @Test
    fun test_fetchItemFeeIdSuccess() = runBlocking {
        // Given
        coEvery { remoteRepository.fetchItemFee(itemFee.id) } returns response

        // When
        val result = fetchRemoteItemFeeUseCase(itemFee.id)

        // Then
        result.collect {
            when (it) {
                is Resource.Loading -> { }
                is Resource.Success -> {
                    println("Item: ${it.data}")
                    coVerify { remoteRepository.fetchItemFee(itemFee.id) }
                    val feeItem = remoteItemMapper.mapToDomain(itemFee)
                    coVerify { localRepository.insertItemFee(feeItem) }

                    assertThat(it.data.id).isEqualTo(itemFee.id)
                }
                is Resource.Error -> { }
            }
        }
    }

    @Test
    fun test_fetchItemIdFailure() = runBlocking {
        // Given
        val errorResponse = RemoteResponseEntity(
            response = null,
            responseCode = 90999,
            responseMessage = "No item id found"
        )
        coEvery { remoteRepository.fetchItemFee(itemFee.id) } returns errorResponse

        // When
        val result = fetchRemoteItemFeeUseCase(itemFee.id)

        // Then
        result.collect {
            when (it) {
                is Resource.Loading -> { }
                is Resource.Success -> { }
                is Resource.Error -> {
                    assertThat(it.exception).isNotEmpty()
                    assertThat(it.exception).isEqualTo("No item id found")
                }
            }
        }
    }
}