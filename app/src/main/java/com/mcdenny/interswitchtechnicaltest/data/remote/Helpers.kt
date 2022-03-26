package com.mcdenny.interswitchtechnicaltest.data.remote

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.mcdenny.interswitchtechnicaltest.domain.model.ErrorMessage
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.util.*

/**
 * A helper method that extracts and formats the error returned from an endpoint
 */
fun Throwable.resolveError(): String {
    return when (this) {
        is SocketTimeoutException -> {
            "Connection failed"
        }
        is ConnectException -> {
            "No internet access"
        }
        is UnknownHostException -> {
            "No internet access"
        }

        is HttpException -> {
            try {
                this.response()?.errorBody()?.charStream()?.let {
                    Gson().fromJson(it, ErrorMessage::class.java).responseMessage
                }.toString()
            } catch (ex: Exception) {
                "Connection failed"
            }
        }

        else -> {
            "Error occurred"
        }
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