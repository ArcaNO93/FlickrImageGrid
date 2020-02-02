package com.example.ilcarro.ui.viewModels.userFlow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.implementations.UserProcessingUseCasesImpl
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class LogInViewModel @Inject constructor() : ViewModel() {

    private var list = mutableListOf(false, false)

    @Inject
    lateinit var userProcessingUseCases: UserProcessingUseCasesImpl

    private val _mButtonClickability = MediatorLiveData<MutableList<Boolean>>()
    val mButtonClickability: LiveData<MutableList<Boolean>>
        get() = _mButtonClickability

    private val _mLoginStatus = MutableLiveData<Boolean>()
    val mLoginStatus: LiveData<Boolean>
        get() = _mLoginStatus

    private val _mLoginValid = MutableLiveData<Pair<Boolean, String?>>()
    val mLoginValid: LiveData<Pair<Boolean, String?>>
        get() = _mLoginValid

    private val _mPasswordValid = MutableLiveData<Pair<Boolean, String?>>()
    val mPasswordValid: LiveData<Pair<Boolean, String?>>
        get() = _mPasswordValid

    init {
        _mButtonClickability.addSource(_mLoginValid) {
            list[0] = it.first
            _mButtonClickability.postValue(list)
        }

        _mButtonClickability.addSource(_mPasswordValid) {
            list[1] = it.first
            _mButtonClickability.postValue(list)
        }
    }

    fun loginUser(user: LoginUserUI) =
        userProcessingUseCases.loginUser(user)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            _mLoginStatus.value = true
        },{
            _mLoginStatus.value = false
        })

    fun validateUserLogin(userLogin: String) =
        userProcessingUseCases.validateUserEmail(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mLoginValid.postValue(Pair(true, ""))
            }, {
                _mLoginValid.postValue(Pair(false, it.message))
            })

    fun validateUserPassword(userPassword: String) =
        userProcessingUseCases.validateUserPassword(userPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mPasswordValid.postValue(Pair(true, ""))
            }, {
                _mPasswordValid.postValue(Pair(false, it.message))
            })

}