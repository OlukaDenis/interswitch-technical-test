package com.mcdenny.interswitchtechnicaltest.data.remote

import com.google.gson.Gson
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

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
                this.localizedMessage!!
            } catch (ex: Exception) {
                "Connection failed"
            }
        }

        else -> {
            "Error occurred"
        }
    }
}