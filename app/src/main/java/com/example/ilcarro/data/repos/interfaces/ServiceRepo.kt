package com.example.ilcarro.data.repos.interfaces

interface ServiceRepo {
    fun saveToken(token: String)
    fun getToken(): String?
    fun saveIsLogged(isLogged: Boolean)
    fun getIsLogged(): Boolean
    fun updateToken(newPassword: String)
}