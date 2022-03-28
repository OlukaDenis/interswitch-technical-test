/*
 * Copyright (c) 2022   Denis Oluka
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

package com.mcdenny.interswitchtechnicaltest.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mcdenny.domain.model.ItemFee
import com.mcdenny.interswitchtechnicaltest.R
import com.mcdenny.interswitchtechnicaltest.databinding.ActivityMainBinding
import com.mcdenny.interswitchtechnicaltest.presentation.utils.DialogClickLister
import com.mcdenny.interswitchtechnicaltest.presentation.utils.DialogUtils.removeFocus
import com.mcdenny.interswitchtechnicaltest.presentation.utils.DialogUtils.showBottomDialog
import com.mcdenny.interswitchtechnicaltest.presentation.utils.formatDateTime
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        with(binding) {
            etSearch.doOnTextChanged { text, _, _, _ ->
                if (text.toString().isNotEmpty()) tilSearch.error = null
            }
            btnFind.setOnClickListener {
                removeFocus()
                if (etSearch.text.toString().isEmpty()) {
                    tilSearch.error = "Please provide a transaction ID"
                } else {
                    viewModel.searchItemFee(etSearch.text.toString())
                }
            }

            cardClearItems.setOnClickListener {
                showBottomDialog(object : DialogClickLister {
                    override fun onOkClicked() {
                        viewModel.clearItems()
                    }
                })
            }
        }

        observeState()
    }

    @SuppressLint("NewApi")
    private fun observeState() {
        viewModel.itemFeeState.observe(this) {
            Timber.d("Transaction: $it")
            with(binding) {
                when (it) {
                    is ItemFeeState.Initial -> {
                        cardContent.isVisible = false
                        clError.isVisible = true
                        mtvErrorMessage.text = getString(R.string.not_transaction_selected)
                    }

                    is ItemFeeState.Loading -> {
                        loading.isVisible = true
                        clError.isVisible = false
                        cardContent.isVisible = false
                    }

                    is ItemFeeState.Success -> {
                        loading.isVisible = false
                        clError.isVisible = false
                        cardContent.isVisible = true

                        val transaction = it.data
                        populateDetails(transaction)
                    }

                    is ItemFeeState.Error -> {
                        loading.isVisible = false
                        clError.isVisible = true
                        cardContent.isVisible = false

                        Snackbar.make(
                            binding.root,
                            it.message,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    @SuppressLint("NewApi")
    private fun populateDetails(transaction: ItemFee) {
        with(binding) {

            mtvItemName.text = transaction.name
            mtvStatus.text =
                if (transaction.isActive) getString(R.string.active) else getString(R.string.inactive)
            mtvIssueDate.text = transaction.issueDate.formatDateTime()

            mtvTax.text = transaction.withholdingTax.toString()
            mtvVat.text = transaction.vat.toString()
            mtvServiceCharge.text = transaction.providerServiceCharge.toString()

            if (transaction.feeGroups.isEmpty()) {
                mtvGroupLabel.isVisible = false
                groupRecycler.isVisible = false
            }

            groupRecycler.apply {
                adapter = FeeGroupAdapter(transaction.feeGroups)
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
            }

        }
    }
}