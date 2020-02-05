package com.example.ilcarro.utils

object Validator {

    var error: Throwable? = null
    private val passwordRegex = arrayOf(
        Pair(Regex("^.*(?=.*[0-9]).*$"),"Password must contain at least one digit"),
        Pair(Regex("^.*(?=.*[a-z]).*$"), "Password must contain at least one lowercase letter"),
        Pair(Regex("^.*(?=.*[A-Z]).*$"), "Password must contain at least one uppercase letter"),
        Pair(Regex("^.*(?!\\S+\$).*$"), "Password must not contain spaces"),
        Pair(Regex("^.{8,}\$"), "Password should be at least 8 characters long")
    )
    private val fullNameRegex = Regex("^[a-zA-Z]+\$")

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

    fun validateFullName(fullName: String): Boolean {
        if(fullName.isEmpty()) {
            error = IllegalArgumentException("Field can\'t be empty")
            return false
        }
        if(!fullName.matches(fullNameRegex)) {
            error = IllegalArgumentException("Field can include only letters")
            return false
        }

        return true
    }

}