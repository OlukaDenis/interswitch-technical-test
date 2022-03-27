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

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mcdenny.interswitchtechnicaltest.domain.model.FeeGroup
import com.mcdenny.interswitchtechnicaltest.domain.model.PayConfiguration

class Converters {

    @TypeConverter
    fun fromList(value: List<String>) = Gson().toJson(value)

    @TypeConverter
    fun toList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun fromFeeGroup(value: List<FeeGroup>): String = Gson().toJson(value)

    @TypeConverter
    fun toFeeGroup(value: String) = Gson().fromJson(value, Array<FeeGroup>::class.java).toList()

    @TypeConverter
    fun fromConfiguration(value: List<PayConfiguration>): String = Gson().toJson(value)

    @TypeConverter
    fun toConfiguration(value: String) =
        Gson().fromJson(value, Array<PayConfiguration>::class.java).toList()
//
//    @TypeConverter
//    fun fromResponse(value: Response): String = Gson().toJson(value)
//
//    @TypeConverter
//    fun toResponse(value: String): Response = Gson().fromJson(value, Response::class.java)

}