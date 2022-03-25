package com.mcdenny.interswitchtechnicaltest.presentation.ui

import com.mcdenny.interswitchtechnicaltest.domain.model.Transaction

data class TransactionState(
    val loading: Boolean = true,
    val transaction: Transaction? = null,
    val failure: String? = null
)