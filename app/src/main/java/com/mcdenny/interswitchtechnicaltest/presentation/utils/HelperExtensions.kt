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

package com.mcdenny.interswitchtechnicaltest.presentation.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mcdenny.interswitchtechnicaltest.R
import com.mcdenny.interswitchtechnicaltest.databinding.LayoutBottomDialogViewBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.util.*

object DialogUtils {

    fun <A : Activity> A.showBottomDialog(successDialogClickLister: DialogClickLister) {

        val dialog = BottomSheetDialog(this, R.style.AppBottomSheetDialogTheme)
        dialog.setCancelable(false)

        val dialogBinding = LayoutBottomDialogViewBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)

        with(dialogBinding) {

            btnOkay.setOnClickListener {
                dialog.dismiss()
                if (!dialog.isShowing) {
                    successDialogClickLister.onOkClicked()
                }
            }

            btnCancel.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.show()
    }

    fun Activity.removeFocus() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun String.formatDateTime(): String {
//    val timestamp = this.replace(" ", "T")
    val localDateTime = LocalDateTime.parse(this)
    val dateFormatter = DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .appendPattern("MMM d, yyyy h:mm a")
        .toFormatter(Locale.ENGLISH)

    return localDateTime.format(dateFormatter)
}

interface DialogClickLister {
    fun onOkClicked()
}