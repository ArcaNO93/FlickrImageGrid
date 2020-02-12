package com.example.ilcarro.ui.viewModels.userFlow

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.implementations.UserProcessingUseCasesImpl
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.utils.State
import com.example.ilcarro.utils.ResponseHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class LogInViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var userProcessingUseCases: UserProcessingUseCasesImpl

    private val _mLoginStatus = MutableLiveData<State>()
    val mLoginStatus: LiveData<State>
        get() = _mLoginStatus

    @SuppressLint("CheckResult")
    fun loginUser(user: LoginUserUI) {
        _mLoginStatus.postValue(State.LOADING)
        userProcessingUseCases.loginUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    _mLoginStatus.postValue(State.LOADED)
                }, {
                    _mLoginStatus.postValue(State.fail(ResponseHandler.parseException(it)))
                })
    }
}