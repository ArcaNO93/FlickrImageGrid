package com.example.ilcarro.utils

import retrofit2.HttpException
import java.io.IOException

object ResponseHandler {
    fun parseException(exception: Throwable) =
        when (exception) {
            is HttpException -> parseHttpResponse(exception.code())
            is IOException -> "Network error"
            else -> "Unknown error"
        }

    private fun parseHttpResponse(response: Int) =
        when (response) {
            400 -> "Bad request"
            401 -> "Unauthorized"
            403 -> "Forbidden"
            404 -> "Not found"
            409 -> "Conflict"
            else -> "Unknown error"
        }
}
