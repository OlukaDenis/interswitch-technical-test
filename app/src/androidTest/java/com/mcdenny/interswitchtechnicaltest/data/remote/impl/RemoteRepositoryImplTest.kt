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

package com.mcdenny.interswitchtechnicaltest.data.remote.impl

import com.google.common.truth.Truth.assertThat
import com.mcdenny.interswitchtechnicaltest.data.remote.ApiService
import com.mcdenny.interswitchtechnicaltest.data.remote.utils.FakeServer
import com.mcdenny.interswitchtechnicaltest.data.remote.utils.JsonReader.getJson
import com.mcdenny.interswitchtechnicaltest.domain.repository.RemoteRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidTest
class RemoteRepositoryImplTest {

    private val fakeServer = FakeServer()
    private lateinit var remoteRepository: RemoteRepository
    private lateinit var service: ApiService
    private val findItemFeePath = "/Item/fee/${30112}"
    private val itemNotFoundPath = "/Item/fee/${100000}"
    private val notFoundResponse = MockResponse().setResponseCode(404)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var retrofitBuilder: Retrofit.Builder

    @Before
    fun setUp() {
        fakeServer.start()
        hiltRule.inject()

        service = retrofitBuilder
            .baseUrl(fakeServer.baseEndpoint)
            .build()
            .create(ApiService::class.java)

        remoteRepository = RemoteRepositoryImpl(service)
    }

    @After
    fun teardown() {
        fakeServer.shutdown()
    }

    @Test
    fun fetchItemFee_success() = runBlocking {
        // Given
        val expectedItemFeeId = 30112L
        fakeServer.mockServer.dispatcher = setItemFeeDispatcher()

        // When
        val itemFeeResponse = remoteRepository.fetchItemFee(30112L)
        println("Item fee: $itemFeeResponse")

        // Then
        val itemFee = itemFeeResponse.response
        assertThat(itemFee?.id).isEqualTo(expectedItemFeeId)
    }

    @Test
    fun fetchUnknownItemFee() = runBlocking {
        // Given
        fakeServer.mockServer.dispatcher = setItemFeeDispatcher()

        // When
        val itemFeeResponse = remoteRepository.fetchItemFee(100000)
        println("Item fee: $itemFeeResponse")

        // Then
        assertThat(itemFeeResponse.responseMessage).isNotNull()
        assertThat(itemFeeResponse.responseMessage).isEqualTo("ItemFee with the specified Id is not found")
    }

    private fun setItemFeeDispatcher() = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            val path = request.path ?: return notFoundResponse

            return with(path) {
                when {
                    contains(findItemFeePath) -> {
                        println("Finding item: $findItemFeePath")
                        MockResponse().setResponseCode(200).setBody(getJson("itemFee.json"))
                    }
                    contains(itemNotFoundPath) -> {
                        println("Item not found: $itemNotFoundPath")
                        MockResponse().setResponseCode(200).setBody(getJson("errorResponse.json"))
                    }
                    else -> {
                        println("not found: $path")
                        notFoundResponse
                    }
                }
            }
        }
    }
}