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
import com.mcdenny.interswitchtechnicaltest.data.remote.model.RemoteItemFee
import com.mcdenny.interswitchtechnicaltest.domain.model.ItemFee
import com.mcdenny.interswitchtechnicaltest.domain.repository.LocalRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Flow

class FindCachedItemFeeUseCaseTest {
    private val availableItemId = 30122L
    private val emptyItemId = 10000L

    private val itemFee = ItemFee(
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

    @MockK
    lateinit var localRepository: LocalRepository

    private lateinit var findCachedItemFeeUseCase: FindCachedItemFeeUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        findCachedItemFeeUseCase = FindCachedItemFeeUseCase(localRepository)
    }

    @Test
    fun test_FindCachedItem_Success() = runBlocking {
        // Given
        val itemFlow = flow { emit(listOf(itemFee))  }
        coEvery { localRepository.findItemFee(availableItemId) } returns itemFlow

        // When
        val result = findCachedItemFeeUseCase(availableItemId)

        // Then
        result.collect {
            coVerify { localRepository.findItemFee(availableItemId) }
            assertThat(it).isNotEmpty()
            assertThat(it[0].id).isEqualTo(30122L)
        }
    }

    @Test
    fun test_FindCachedItem_NotFound() = runBlocking {
        // Given
        val itemFlow = flow { emit(emptyList<ItemFee>())}
        coEvery { localRepository.findItemFee(emptyItemId) } returns itemFlow

        // When
        val result = findCachedItemFeeUseCase(emptyItemId)

        // Then
        result.collect {
            coVerify { localRepository.findItemFee(emptyItemId) }
            assertThat(it).isEmpty()
        }
    }
}