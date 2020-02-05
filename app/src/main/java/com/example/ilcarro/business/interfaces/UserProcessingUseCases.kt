package com.example.ilcarro.business.interfaces

import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI
import com.example.ilcarro.data.dto.user.ui.UserUI
import io.reactivex.Completable
import io.reactivex.Single

interface UserProcessingUseCases {
    fun registerUser(registerUser: RegisterUserUI): Completable
    fun loginUser(user: LoginUserUI): Completable
    fun logOut()
    fun getUserData(): Single<UserUI>
    fun updateUser(user: UpdateUserUI): Completable
    fun deleteUser(): Completable
    fun getIsLogged(): Boolean
}