package com.example.ilcarro.data.repos

import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI
import io.reactivex.Completable

interface UserProcessingRepo {
    fun registerUser(user: RegisterUserUI): Completable
    fun loginUser(user: LoginUserUI): Completable
    fun updateUser(user:UpdateUserUI): Completable
    fun deleteUser(): Completable
}