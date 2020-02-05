package com.example.ilcarro.data.repos.implementations

import android.content.SharedPreferences
import android.util.Base64
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.repos.interfaces.ServiceRepo
import io.reactivex.Single
import okhttp3.Credentials
import javax.inject.Inject

@ActivityScope
class ServiceRepoImpl @Inject constructor() : ServiceRepo {

    @Inject
    lateinit var mService: SharedPreferences

    override fun saveToken(token: String) =
        mService.edit().putString("token", token).apply()

    override fun getToken(): String? =
        mService.getString("token", "")

    override fun saveIsLogged(isLogged: Boolean) =
        mService.edit().putBoolean("isLogged", isLogged).apply()

    override fun getIsLogged() =
        mService.getBoolean("isLogged", false)

    override fun updateToken(newPassword: String) =
        saveToken(
            Credentials.basic(
                Base64.decode(
                    getToken()?.split(" ")?.get(1),
                    Base64.NO_WRAP
                ).toString().split(":").toMutableList()[0], newPassword
            )
        )
}
