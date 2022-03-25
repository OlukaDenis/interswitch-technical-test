package com.mcdenny.interswitchtechnicaltest.domain.model

data class ResponseEntity(
    val response: Transaction?,
    val responseCode: Long,
    val responseMessage: String
)