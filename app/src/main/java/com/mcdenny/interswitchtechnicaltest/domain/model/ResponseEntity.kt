package com.mcdenny.interswitchtechnicaltest.domain.model

data class ResponseEntity(
    val response: ItemFee?,
    val responseCode: Long,
    val responseMessage: String
)