package com.example.ilcarro.data.repos

import android.util.Base64
import android.util.Log
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.api.IlCarroAPI
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI
import com.example.ilcarro.utils.Mapper
import io.reactivex.Completable
import okhttp3.Credentials
import javax.inject.Inject

@FragmentScope
class UserProcessingRepoImpl @Inject constructor() : UserProcessingRepo {

    @Inject
    lateinit var mServiceRepo: ServiceRepoImpl

    @Inject
    lateinit var mRetrofit: IlCarroAPI

    override fun registerUser(user: RegisterUserUI): Completable {
        val token = Credentials.basic(user.login, user.password)
        return Completable.fromSingle(mRetrofit.registerUser(token, Mapper.toRegisterUserRequest(user))
            .doOnSuccess {
                mServiceRepo.saveToken(token)
            })
    }

    override fun loginUser(user: LoginUserUI): Completable {
        val token = Credentials.basic(user.login, user.password)
        return Completable.fromSingle(mRetrofit.loginUser(token)
            .doOnSuccess {
                mServiceRepo.saveToken(token)
                mServiceRepo.saveIsLogged(true)
            })
    }

    override fun updateUser(user: UpdateUserUI) =
        Completable.fromSingle(
            mRetrofit.updateUser(
                mServiceRepo.getToken(),
                Base64.encodeToString(user.newPassword.toByteArray(), Base64.NO_WRAP),
                Mapper.toUpdateUserRequest(user)
            ).doOnSuccess {
                mServiceRepo.updateToken(user.newPassword)
            }
        )
}