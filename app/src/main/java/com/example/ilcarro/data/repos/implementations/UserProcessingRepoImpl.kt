package com.example.ilcarro.data.repos.implementations

import android.util.Base64
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.api.UserProcessingAPI
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI
import com.example.ilcarro.data.repos.interfaces.UserProcessingRepo
import com.example.ilcarro.utils.Mapper
import io.reactivex.Completable
import okhttp3.Credentials
import retrofit2.Retrofit
import javax.inject.Inject

@ActivityScope
class UserProcessingRepoImpl @Inject constructor() : UserProcessingRepo {

    @Inject
    lateinit var mServiceRepo: ServiceRepoImpl

    @Inject
    lateinit var mRetrofit: Retrofit
    private val mService by lazy {
        mRetrofit.create(UserProcessingAPI::class.java)
    }

    override fun registerUser(user: RegisterUserUI): Completable {
        val token = Credentials.basic(user.login, user.password)
        return Completable.fromSingle(mService.registerUser(token, Mapper.toRegisterUserRequest(user))
            .doOnSuccess {
                mServiceRepo.saveToken(token)
            })
    }

    override fun loginUser(user: LoginUserUI): Completable {
        val token = Credentials.basic(user.login, user.password)
        return Completable.fromSingle(mService.loginUser(token)
            .doOnSuccess {
                mServiceRepo.saveToken(token)
                mServiceRepo.saveIsLogged(true)
            })
    }

    override fun logOut() {
        mServiceRepo.saveToken("")
        mServiceRepo.saveIsLogged(false)
    }

    override fun getUserData() =
        mService.loginUser(mServiceRepo.getToken()).map {
            Mapper.toUserUI(it)
        }

    override fun updateUser(user: UpdateUserUI) =
        Completable.fromSingle(
            mService.updateUser(
                mServiceRepo.getToken(),
                Base64.encodeToString(user.newPassword.toByteArray(), Base64.NO_WRAP),
                Mapper.toUpdateUserRequest(user)
            ).doOnSuccess {
                mServiceRepo.updateToken(user.newPassword)
            }
        )

    override fun deleteUser() = mService.deleteUser(mServiceRepo.getToken())

    override fun getIsLogged() = mServiceRepo.mIsLogged
}