package com.example.ilcarro.utils

class Event<out T>(private val content: T) {
    var isHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if(isHandled) {
            null
        } else {
            isHandled = true
            content
        }
    }

    fun forceTakeContent() = content
}