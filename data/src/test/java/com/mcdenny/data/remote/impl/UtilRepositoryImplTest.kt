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

import com.google.common.truth.Truth.assertThat
import com.mcdenny.domain.model.ErrorMessage
import com.mcdenny.domain.repository.UtilRepository
import io.mockk.MockKAnnotations
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class UtilRepositoryImplTest {

    private lateinit var utilRepository: UtilRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        utilRepository = UtilRepositoryImpl()
    }

    @Test
    fun test_getNetworkError_timeout() {
        // Given
        val exception = SocketTimeoutException()
        val throwable = Throwable(exception).cause

        // When
        val result = utilRepository.getNetworkError(throwable!!)

        // Then
        assertThat(result).isEqualTo("Connection failed")
    }

    @Test
    fun test_getNetworkError_connectionError() {
        // Given
        val exception = ConnectException()
        val throwable = Throwable(exception).cause

        // When
        val result = utilRepository.getNetworkError(throwable!!)

        // Then
        assertThat(result).isEqualTo("No internet access")
    }

    @Test
    fun test_getNetworkError_unknownError() {
        // Given
        val exception = UnknownHostException()
        val throwable = Throwable(exception).cause

        // When
        val result = utilRepository.getNetworkError(throwable!!)

        // Then
        assertThat(result).isEqualTo("Unable to reach the server")
    }
}