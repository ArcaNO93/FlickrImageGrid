package com.example.ilcarro.business

import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI
import com.example.ilcarro.data.repos.UserProcessingRepoImpl
import io.reactivex.Completable
import javax.inject.Inject

@FragmentScope
class UserProcessingUseCasesImpl @Inject constructor() : UserProcessingUseCases {

    @Inject
    lateinit var mUserProcessingRepo: UserProcessingRepoImpl

    override fun registerUser(registerUser: RegisterUserUI): Completable = mUserProcessingRepo.registerUser(registerUser)

    override fun loginUser(user: LoginUserUI) = mUserProcessingRepo.loginUser(user)

    override fun updateUser(user: UpdateUserUI) = mUserProcessingRepo.updateUser(user)

    override fun deleteUser() = mUserProcessingRepo.deleteUser()
}