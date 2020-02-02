package com.example.ilcarro.business.implementations

import com.example.ilcarro.business.interfaces.UserProcessingUseCases
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI
import com.example.ilcarro.data.repos.implementations.UserProcessingRepoImpl
import com.example.ilcarro.utils.Validator
import io.reactivex.Completable
import javax.inject.Inject

@FragmentScope
class UserProcessingUseCasesImpl @Inject constructor() : UserProcessingUseCases {

    @Inject
    lateinit var mUserProcessingRepo: UserProcessingRepoImpl

    override fun registerUser(registerUser: RegisterUserUI) =
        mUserProcessingRepo.registerUser(registerUser)

    override fun loginUser(user: LoginUserUI) =
        mUserProcessingRepo.loginUser(user)

    override fun updateUser(user: UpdateUserUI) =
        mUserProcessingRepo.updateUser(user)

    override fun deleteUser() =
        mUserProcessingRepo.deleteUser()

    fun validateUserEmail(userLogin: String): Completable {
        val validator = Validator
        return if (validator.validateEmail(userLogin))
            Completable.complete()
        else
            Completable.error(validator.error)
    }

    fun validateUserPassword(userPassword: String): Completable {
        val validator = Validator
        return if (validator.validatePassword(userPassword))
            Completable.complete()
        else
            Completable.error(validator.error)
    }

}