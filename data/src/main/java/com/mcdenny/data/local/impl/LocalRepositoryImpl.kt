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

package com.mcdenny.data.local.impl

import com.mcdenny.data.local.ItemFeeDao
import com.mcdenny.domain.model.ItemFee
import com.mcdenny.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val itemFeeDao: ItemFeeDao
) : LocalRepository {
    override suspend fun insertItemFee(itemFee: ItemFee) {
       itemFeeDao.insertItemFee(itemFee)
    }

    override suspend fun deleteItemFee(itemFee: ItemFee) {
        itemFeeDao.deleteItemFee(itemFee)
    }

    override suspend fun clearItemFees() {
        itemFeeDao.clearItemFees()
    }

    override fun findItemFee(itemFeeId: Long): Flow<List<ItemFee>> {
       return itemFeeDao.findItemFee(itemFeeId)
    }
}