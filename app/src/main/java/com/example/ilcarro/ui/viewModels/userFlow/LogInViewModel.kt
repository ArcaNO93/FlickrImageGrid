package com.example.ilcarro.ui.viewModels.userFlow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.UserProcessingUseCasesImpl
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class LogInViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var userProcessingUseCases: UserProcessingUseCasesImpl

    private val _mLoginStatus = MutableLiveData<Boolean>()
    val mLoginStatus: LiveData<Boolean>
        get() = _mLoginStatus

    fun loginUser(user: LoginUserUI) =
        userProcessingUseCases.loginUser(user)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            _mLoginStatus.value = true
        },{
            _mLoginStatus.value = false
        })
}