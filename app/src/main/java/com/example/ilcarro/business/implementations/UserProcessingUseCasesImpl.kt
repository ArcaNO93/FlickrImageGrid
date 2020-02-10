package com.example.ilcarro.business.implementations

import com.example.ilcarro.business.interfaces.UserProcessingUseCases
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI
import com.example.ilcarro.data.repos.implementations.UserProcessingRepoImpl
import com.example.ilcarro.utils.Validator
import io.reactivex.Completable
import javax.inject.Inject

@ActivityScope
class UserProcessingUseCasesImpl @Inject constructor(
    private val mUserProcessingRepo: UserProcessingRepoImpl
) : UserProcessingUseCases {

    override fun registerUser(registerUser: RegisterUserUI) =
        mUserProcessingRepo.registerUser(registerUser)

    override fun loginUser(user: LoginUserUI) =
        mUserProcessingRepo.loginUser(user)

    override fun logOut() =
        mUserProcessingRepo.logOut()

    override fun getUserData() =
        mUserProcessingRepo.getUserData()

    override fun updateUser(user: UpdateUserUI) =
        mUserProcessingRepo.updateUser(user)

    override fun deleteUser() =
        mUserProcessingRepo.deleteUser()

    override fun getIsLogged() =
        mUserProcessingRepo.getIsLogged()

    fun validateUserFullName(userFullName: String): Completable {
        return if(Validator.validateIfLettersOnly(userFullName))
            Completable.complete()
        else
            Completable.error(Validator.error)
    }

    fun validateEmail(userEmail: String): Completable {
        return if(Validator.validateEmail(userEmail))
            Completable.complete()
        else
            Completable.error(Validator.error)
    }

    fun validatePassword(userPassword: String): Completable {
        return if(Validator.validatePassword(userPassword))
            Completable.complete()
        else
            Completable.error(Validator.error)
    }
}