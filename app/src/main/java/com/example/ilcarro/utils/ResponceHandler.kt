package com.example.ilcarro.utils

class ResponceHandler {
    companion object {
        fun parseResponce(response: Int) =
            when(response) {
                200 -> "Success"
                400 -> "Bad request"
                401 -> "Unauthorized"
                403 -> "Forbidden"
                404 -> "Not found"
                409 -> "Conflict"
                else -> ""
            }
    }
}