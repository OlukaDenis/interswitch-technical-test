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

package com.mcdenny.data.remote.mappers

import com.mcdenny.data.BaseMapper
import com.mcdenny.data.remote.model.RemoteResponseEntity
import com.mcdenny.domain.model.ResponseEntity
import javax.inject.Inject

class RemoteResponseMapper @Inject constructor(
    private val remoteItemFeeMapper: RemoteItemFeeMapper
) : BaseMapper<RemoteResponseEntity, ResponseEntity> {
    override fun mapToDomain(entity: RemoteResponseEntity): ResponseEntity {
        return ResponseEntity(
            response = entity.response?.let { remoteItemFeeMapper.mapToDomain(it) },
            responseCode = entity.responseCode ?: 0,
            responseMessage = entity.responseMessage.orEmpty()
        )
    }
}