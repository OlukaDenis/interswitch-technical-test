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

package com.mcdenny.interswitchtechnicaltest.data.remote.mappers

import com.mcdenny.interswitchtechnicaltest.data.BaseMapper
import com.mcdenny.interswitchtechnicaltest.data.remote.model.RemoteTransaction
import com.mcdenny.interswitchtechnicaltest.domain.model.Transaction
import javax.inject.Inject

class RemoteTransactionMapper @Inject constructor(
    private val remoteFeeGroupMapper: RemoteFeeGroupMapper,
    private val remotePayConfigurationMapper: RemotePayConfigurationMapper
): BaseMapper<RemoteTransaction, Transaction> {

    override fun mapToDomain(entity: RemoteTransaction): Transaction {
        return Transaction(
            id = entity.id,
            excise = entity.excise ?: 0.0,
            exciseTaxAccount = entity.exciseTaxAccount.orEmpty(),
            feeGroups = entity.feeGroups?.map { remoteFeeGroupMapper.mapToDomain(it) }.orEmpty(),
            payConfiguration = entity.payConfiguration?.map { remotePayConfigurationMapper.mapToDomain(it) }.orEmpty(),
            hasProviderServiceCharge = entity.hasProviderServiceCharge ?: false,
            isActive = entity.isActive ?: false,
            isInclusiveInAmount = entity.isInclusiveInAmount ?: false,
            issueDate = entity.issueDate.orEmpty(),
            name = entity.name.orEmpty(),
            overrideBillerFee = entity.overrideBillerFee ?: false,
            providerServiceCharge = entity.providerServiceCharge ?: 0.0,
            providerServiceChargeAccount = entity.providerServiceChargeAccount.orEmpty(),
            taxAccount = entity.taxAccount.orEmpty(),
            vat = entity.vat ?: 0.0,
            withholdingTax = entity.withholdingTax ?: 0.0,
            withholdingTaxAccount = entity.withholdingTaxAccount.orEmpty()
        )
    }
}