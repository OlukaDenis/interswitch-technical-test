package com.mcdenny.interswitchtechnicaltest.data.remote

import com.mcdenny.interswitchtechnicaltest.data.remote.model.RemoteResponseEntity
import com.mcdenny.interswitchtechnicaltest.domain.model.ResponseEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{itemFeeId}?getRelatedData=true")
    suspend fun fetchItemFee(
        @Path("itemFeeId") itemFeeId: Long
    ): RemoteResponseEntity
}