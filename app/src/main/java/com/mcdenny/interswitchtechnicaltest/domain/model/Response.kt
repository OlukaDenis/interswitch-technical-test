package com.mcdenny.interswitchtechnicaltest.domain.model

data class Response(
    val excise: Double,
    val exciseTaxAccount: String,
    val feeGroups: List<FeeGroup>,
    val hasProviderServiceCharge: Boolean,
    val id: Long,
    val isActive: Boolean,
    val isInclusiveInAmount: Boolean,
    val issueDate: String,
    val itemFeeMapSettings: List<Any>?,
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