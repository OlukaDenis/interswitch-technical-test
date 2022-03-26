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

package com.mcdenny.interswitchtechnicaltest.presentation.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mcdenny.interswitchtechnicaltest.data.remote.formatDateTime
import com.mcdenny.interswitchtechnicaltest.databinding.LayoutItemGroupBinding
import com.mcdenny.interswitchtechnicaltest.domain.model.FeeGroup

class FeeGroupAdapter(
    private val list: List<FeeGroup>
) : RecyclerView.Adapter<FeeGroupAdapter.FeeGroupViewHolder>() {
    inner class FeeGroupViewHolder(
        val binding: LayoutItemGroupBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeeGroupAdapter.FeeGroupViewHolder {
        return FeeGroupViewHolder(
            LayoutItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: FeeGroupAdapter.FeeGroupViewHolder, position: Int) {
        val item = list[position]

        with(holder.binding) {
            if (item.name.isNotEmpty()) {
                mtvItemName.text = item.name
            }

            if (item.issueDate.isNotEmpty()) {
                mtvIssueDate.text = item.issueDate.formatDateTime()
            }
        }
    }

    override fun getItemCount(): Int = list.size
}