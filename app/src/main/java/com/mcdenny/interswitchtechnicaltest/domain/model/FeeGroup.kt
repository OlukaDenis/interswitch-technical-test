package com.mcdenny.interswitchtechnicaltest.domain.model

data class FeeGroup(
    val description: String,
    val id: Long,
    val isActive: Boolean,
    val issueDate: String,
    val itemFeeId: Long,
    val itemId: Long,
    val name: String
)