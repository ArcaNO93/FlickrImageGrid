package com.example.ilcarro.data.repos.implementations

import android.content.SharedPreferences
import android.util.Base64
import android.util.Log
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.repos.interfaces.ServiceRepo
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.subjects.BehaviorSubject
import okhttp3.Credentials
import javax.inject.Inject

@ActivityScope
class ServiceRepoImpl @Inject constructor(
    private val mService: SharedPreferences
) : ServiceRepo {

    private val mEmmiter = BehaviorSubject.create<Boolean>()
    val mIsLogged: Observable<Boolean> = mEmmiter
    private val mShPrefChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if(key == "isLogged")
            mEmmiter.onNext(getIsLogged())
    }

    init {
        mEmmiter.onNext(getIsLogged())
        mService.registerOnSharedPreferenceChangeListener(mShPrefChangeListener)
    }

    override fun saveToken(token: String) {
        mService.edit().putString("token", token).apply()
    }

    override fun getToken(): String? =
        mService.getString("token", "")

    override fun saveIsLogged(isLogged: Boolean) {
        mService.edit().putBoolean("isLogged", isLogged).apply()
    }

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
