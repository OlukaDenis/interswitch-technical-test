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

package com.mcdenny.data.remote.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.mcdenny.data.local.AppDatabase
import com.mcdenny.data.local.ItemFeeDao
import com.mcdenny.data.local.di.DatabaseModule
import com.mcdenny.data.local.impl.LocalRepositoryImpl
import com.mcdenny.domain.model.ItemFee
import com.mcdenny.domain.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(DatabaseModule::class)
class LocalRepositoryImplTest {

    private lateinit var localRepository: LocalRepository

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var database: AppDatabase

    @Inject
    lateinit var itemFeeDao: ItemFeeDao

    @Module
    @InstallIn(SingletonComponent::class)
    object TestDatabaseModule {

        @Provides
        fun provideRoomDatabase(): AppDatabase {
            return Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().context,
                AppDatabase::class.java
            ).allowMainThreadQueries()
                .build()
        }

        @Provides
        fun provideItemFeeDao(database: AppDatabase) = database.itemFeeDao()
    }

    @Before
    fun setUp() {
        hiltRule.inject()
        localRepository = LocalRepositoryImpl(itemFeeDao)
    }

    @Test
    fun test_saveItem_findItem() = runBlocking {
        // Given
        val itemFee = ItemFee(
            id = 30122,
            excise =0.0,
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

        // When
        localRepository.insertItemFee(itemFee)
        val savedFee = localRepository.findItemFee(itemFee.id).first()

        // Then
        assertThat(savedFee).isNotEmpty()
        assertThat(savedFee[0].name).isEqualTo("Test")
    }

    @Test
    fun test_saveItem_clearItems() = runBlocking {
        // Given
        val itemFee = ItemFee(
            id = 30122,
            excise =0.0,
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

        // When
        localRepository.insertItemFee(itemFee)
        localRepository.clearItemFees()
        val savedFee = localRepository.findItemFee(itemFee.id).first()

        // Then
        assertThat(savedFee).isEmpty()
    }
}