package com.mcdenny.interswitchtechnicaltest.domain.repository

import com.mcdenny.interswitchtechnicaltest.data.remote.model.RemoteResponseEntity
import com.mcdenny.interswitchtechnicaltest.domain.model.ResponseEntity

interface RemoteRepository {

    suspend fun fetchItemFee(itemFeeId: Long): RemoteResponseEntity
}