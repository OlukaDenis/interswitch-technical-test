package com.mcdenny.interswitchtechnicaltest.domain.model

data class FeeGroup(
    val clientFees: Any?,
    val description: String,
    val id: Long,
    val isActive: Boolean,
    val issueDate: String,
    val item: Any?,
    val itemFeeId: Long,
    val itemId: Long,
    val name: String
)