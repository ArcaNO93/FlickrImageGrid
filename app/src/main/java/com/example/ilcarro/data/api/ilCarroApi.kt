package com.example.ilcarro.data.api

import com.example.ilcarro.data.dto.user.RegisterUserRequest
import com.example.ilcarro.data.dto.user.UpdateUserRequest
import com.example.ilcarro.data.dto.user.User
import io.reactivex.Single
import retrofit2.http.*

interface IlCarroAPI {

    @POST("/registration")
    fun registerUser(
        @Header("Authorization") token: String,
        @Body user: RegisterUserRequest
    ): Single<User>

    @GET("/user/login")
    fun loginUser(@Header("Authorization") token: String): Single<User>

    @PUT("/user")
    fun updateUser(
        @Header("Authorization") token: String?,
        @Header("X-New-Password") newPassword: String,
        @Body user: UpdateUserRequest
    ): Single<User>
}