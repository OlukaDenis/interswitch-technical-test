package com.mcdenny.interswitchtechnicaltest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_fees")
data class ItemFee(
    @PrimaryKey
    val id: Long,
    val excise: Double,
    val exciseTaxAccount: String,
    val feeGroups: List<FeeGroup>,
    val hasProviderServiceCharge: Boolean,
    val isActive: Boolean,
    val isInclusiveInAmount: Boolean,
    val issueDate: String,
    val name: String,
    val overrideBillerFee: Boolean,
    val payConfiguration: List<PayConfiguration>,
    val providerServiceCharge: Double,
    val providerServiceChargeAccount: String,
    val taxAccount: String,
    val vat: Double,
    val withholdingTax: Double,
    val withholdingTaxAccount: String
)