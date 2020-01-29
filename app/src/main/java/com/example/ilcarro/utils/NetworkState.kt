package com.example.ilcarro.utils

enum class STATUS {
    LOADING,
    LOADED,
    FAIL
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
    val status: STATUS,
    val msg: String? = null
) {
    companion object {
        val LOADING = NetworkState(STATUS.LOADING)
        val LOADED = NetworkState(STATUS.LOADED)
        fun fail(msg: String?) = NetworkState(STATUS.FAIL, msg)
    }
}