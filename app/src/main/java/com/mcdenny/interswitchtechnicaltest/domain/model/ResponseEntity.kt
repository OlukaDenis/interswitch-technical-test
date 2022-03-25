package com.mcdenny.interswitchtechnicaltest.domain.model

data class ResponseEntity(
    val response: Response,
    val responseCode: Long,
    val responseMessage: String
)