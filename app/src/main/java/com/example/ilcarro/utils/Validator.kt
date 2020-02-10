package com.example.ilcarro.utils

import android.util.Log

object Validator {

    var error: Throwable? = null
    private val passwordRegex = arrayOf(
        Pair(Regex("^.*(?=.*[0-9]).*$"),"Password must contain at least one digit"),
        Pair(Regex("^.*(?=.*[a-z]).*$"), "Password must contain at least one lowercase letter"),
        Pair(Regex("^.*(?=.*[A-Z]).*$"), "Password must contain at least one uppercase letter"),
        Pair(Regex("^.*(?!\\S+\$).*$"), "Password must not contain spaces"),
        Pair(Regex("^.{8,}\$"), "Password should be at least 8 characters long")
    )
    private val stringOnlyLettersRegex = Regex("^[a-zA-Z]+\$")
    private val stringOnlyLettersAndNumbersRegex = Regex("^[a-zA-Z0-9]+\$")
    private val stringWithoutSpecialSymbols = Regex("^[a-zA-Z0-9][,.][\\S]+\$")

    fun validateEmail(email: String): Boolean {
        if(email.isEmpty()) {
            error = IllegalArgumentException("E-mail can\'t be empty")
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = IllegalArgumentException("Incorrect e-mail")
            return false
        }
        return true
    }

    fun validatePassword(password: String): Boolean {
        var result = true
        val errorText = StringBuilder()
        for (regex in passwordRegex) {
            if (!password.matches(regex.first)) {
                result = false
                errorText.append(regex.second)
                errorText.append("\n")
            }
        }
        error = IllegalArgumentException(errorText.toString())

        return result
    }

    fun validateIfLettersOnly(fullName: String): Boolean {
        if(fullName.isEmpty()) {
            error = IllegalArgumentException("Field can\'t be empty")
            return false
        }
        if(!fullName.matches(stringOnlyLettersRegex)) {
            error = IllegalArgumentException("Field can include only letters")
            return false
        }

        return true
    }

    fun validateLettersAndNumbersOnly(fullName: String): Boolean {
        if(fullName.isEmpty()) {
            error = IllegalArgumentException("Field can\'t be empty")
            return false
        }
        if(!fullName.matches(stringOnlyLettersAndNumbersRegex)) {
            error = IllegalArgumentException("Field can include only letters and numbers")
            return false
        }
        return true
    }

    fun validateNoSpecialSymbols(fullName: String): Boolean {
        if(fullName.isEmpty()) {
            error = IllegalArgumentException("Field can\'t be empty")
            return false
        }
        if(!fullName.matches(stringWithoutSpecialSymbols)) {
            error = IllegalArgumentException("Field should not contain special symbols")
            return false
        }
        return true
    }

    fun validateIfEmpty(string: String): Boolean {
        return if(string.isNotEmpty())
            true
        else {
            error = IllegalArgumentException("Field can\'t be empty")
            false
        }
    }

    fun validateList(element: String, list: List<String>): Boolean {
        return when {
            element.isEmpty() -> {
                error = IllegalArgumentException("Can\'t add an empty field")
                false
            }
            list.contains(element) -> {
                error = IllegalArgumentException("No duplicated fields allowed")
                false
            }
            else -> true
        }
    }
}