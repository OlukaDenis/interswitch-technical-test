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
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.mcdenny.interswitchtechnicaltest.R
import com.mcdenny.interswitchtechnicaltest.data.remote.formatDateTime
import com.mcdenny.interswitchtechnicaltest.databinding.ActivityMainBinding
import com.mcdenny.interswitchtechnicaltest.domain.model.Transaction
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

//        viewModel.searchTransaction("30119")
        with(binding) {
            etSearch.doOnTextChanged { text, start, before, count ->
                if (text.toString().isNotEmpty()) tilSearch.error = null
            }
            btnFind.setOnClickListener {
                if (etSearch.text.toString().isEmpty()) {
                    tilSearch.error = "Please provide a transaction ID"
                } else {
                    viewModel.searchTransaction(etSearch.text.toString())
                }
            }
        }

        observeState()
    }

    @SuppressLint("NewApi")
    private fun observeState() {
        viewModel.transactionState.observe(this) {
            Timber.d("Transaction: $it")
            with(binding) {
                when(it) {
                    is TransactionState.Initial -> {
                        cardContent.isVisible = false
                        clError.isVisible = true
                        mtvErrorMessage.text = getString(R.string.not_transaction_selected)
                    }
                    is TransactionState.Loading -> {
                        loading.isVisible = true
                        clError.isVisible = false
                        cardContent.isVisible = false
                    }

                    is TransactionState.Success -> {
                        loading.isVisible = false
                        clError.isVisible = false
                        cardContent.isVisible = true

                        val transaction = it.data
                        mtvTransactionName.text = transaction.name
                        mtvStatus.text = if (transaction.isActive) getString(R.string.active) else getString(R.string.inactive)
                        mtvIssueDate.text = transaction.issueDate.formatDateTime()
                    }
                    is TransactionState.Error -> {
                        loading.isVisible = false
                        clError.isVisible = true
                        cardContent.isVisible = false

                        mtvErrorMessage.text = it.message
                    }
                }
            }
        }
    }
}