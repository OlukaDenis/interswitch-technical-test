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

package com.mcdenny.data.local

import androidx.room.*
import com.mcdenny.domain.model.ItemFee
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemFeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemFee(itemFee: ItemFee)

    @Query("SELECT * FROM item_fees")
    fun getAllItemFees(): Flow<List<ItemFee>>

    @Query("SELECT * FROM item_fees WHERE id == :itemFeeId")
    fun findItemFee(itemFeeId: Long): Flow<List<ItemFee>>

    @Delete
    suspend fun deleteItemFee(itemFee: ItemFee)

    @Query("DELETE FROM item_fees")
    suspend fun clearItemFees()
}