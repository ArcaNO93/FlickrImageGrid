package com.example.ilcarro.ui.viewModels.userFlow

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.implementations.UserProcessingUseCasesImpl
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.utils.NetworkState
import com.example.ilcarro.utils.ResponseHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class LogInViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var userProcessingUseCases: UserProcessingUseCasesImpl

    private val _mLoginStatus = MutableLiveData<NetworkState>()
    val mLoginStatus: LiveData<NetworkState>
        get() = _mLoginStatus

    @SuppressLint("CheckResult")
    fun loginUser(user: LoginUserUI) {
        _mLoginStatus.postValue(NetworkState.LOADING)
        userProcessingUseCases.loginUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    _mLoginStatus.postValue(NetworkState.LOADED)
                }, {
                    _mLoginStatus.postValue(NetworkState.fail(ResponseHandler.parseException(it)))
                })
    }
}