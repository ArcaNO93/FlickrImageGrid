package com.example.ilcarro.utils

import retrofit2.HttpException
import java.io.IOException

object ResponseHandler {
    fun parseException(exception: Throwable) =
        when (exception) {
            is HttpException -> parseResponse(exception.code())
            is IOException -> "Network error\nPlease check internet connection"
            else -> "Unknown error. Please contact support"
        }

    private fun parseResponse(response: Int) =
        when (response) {
            400 -> "Bad request"
            401 -> "Wrong data. Try again"
            403 -> "Forbidden"
            404 -> "Not found"
            409 -> "Field already exist"
            else -> "Unknown error. Please contact support"
        }
}
