package com.example.ilcarro.data.repos.interfaces

import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI
import com.example.ilcarro.data.dto.user.ui.UserUI
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface UserProcessingRepo {
    fun registerUser(user: RegisterUserUI): Completable
    fun loginUser(user: LoginUserUI): Completable
    fun logOut()
    fun getIsLogged(): Observable<Boolean>
    fun getUserData() : Single<UserUI>
    fun updateUser(user:UpdateUserUI): Completable
    fun deleteUser(): Completable
}