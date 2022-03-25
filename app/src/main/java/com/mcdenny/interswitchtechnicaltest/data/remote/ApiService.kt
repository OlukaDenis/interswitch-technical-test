package com.mcdenny.interswitchtechnicaltest.data.remote

import com.mcdenny.interswitchtechnicaltest.data.remote.model.RemoteResponseEntity
import com.mcdenny.interswitchtechnicaltest.domain.model.ResponseEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{transactionId}?getRelatedData=true")
    suspend fun fetchTransaction(
        @Path("transactionId") transactionId: Long
    ): RemoteResponseEntity
}