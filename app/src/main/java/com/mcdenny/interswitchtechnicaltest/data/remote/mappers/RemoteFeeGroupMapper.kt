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

package com.mcdenny.interswitchtechnicaltest.data.remote.mappers

import com.mcdenny.interswitchtechnicaltest.data.BaseMapper
import com.mcdenny.interswitchtechnicaltest.data.remote.model.RemoteFeeGroup
import com.mcdenny.interswitchtechnicaltest.domain.model.FeeGroup
import java.lang.Exception
import javax.inject.Inject

class RemoteFeeGroupMapper @Inject constructor(): BaseMapper<RemoteFeeGroup, FeeGroup> {
    override fun mapToDomain(entity: RemoteFeeGroup): FeeGroup {
        return FeeGroup(
            id = entity.id ?: throw Exception("Id cannot be null"),
            description = entity.description.orEmpty(),
            isActive = entity.isActive ?: false,
            itemFeeId = entity.itemFeeId ?: 0,
            itemId = entity.itemId ?: 0,
            name = entity.name.orEmpty(),
            issueDate = entity.issueDate.orEmpty()
        )
    }
}