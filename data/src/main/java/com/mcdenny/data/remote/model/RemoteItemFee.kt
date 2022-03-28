/*
 * Copyright (c) 2022 Denis Oluka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.mcdenny.data.remote.model

data class RemoteItemFee(
    val id: Long,
    val excise: Double?,
    val exciseTaxAccount: String?,
    val feeGroups: List<RemoteFeeGroup>?,
    val hasProviderServiceCharge: Boolean?,
    val isActive: Boolean?,
    val isInclusiveInAmount: Boolean?,
    val issueDate: String?,
    val name: String?,
    val overrideBillerFee: Boolean?,
    val payConfiguration: List<RemotePayConfiguration>?,
    val providerServiceCharge: Double?,
    val providerServiceChargeAccount: String?,
    val taxAccount: String?,
    val vat: Double?,
    val withholdingTax: Double?,
    val withholdingTaxAccount: String?
)