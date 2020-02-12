package com.example.ilcarro.utils

enum class STATUS {
    LOADING,
    LOADED,
    FAIL
}

@Suppress("DataClassPrivateConstructor")
data class State private constructor(
    val status: STATUS,
    val msg: String? = null
) {
    companion object {
        val LOADING = State(STATUS.LOADING)
        val LOADED = State(STATUS.LOADED)
        fun fail(msg: String?) = State(STATUS.FAIL, msg)
    }
}