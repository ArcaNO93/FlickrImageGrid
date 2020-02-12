package com.example.ilcarro.ui.viewModels.userFlow

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.implementations.UserProcessingUseCasesImpl
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.user.ui.LoginUserUI
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import com.example.ilcarro.utils.State
import com.example.ilcarro.utils.ResponseHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class RegisterViewModel @Inject constructor() : ViewModel() {

    private var mCheckList: MutableList<Boolean> = mutableListOf(false, false, false, false, false)

    @Inject
    lateinit var userProcessingUseCases: UserProcessingUseCasesImpl

    private val _mButtonClickability = MediatorLiveData<MutableList<Boolean>>()
    val mButtonClickability: LiveData<MutableList<Boolean>>
        get() = _mButtonClickability

    private val _mRegisterComplete: MutableLiveData<State> = MutableLiveData()
    val mRegisterComplete: LiveData<State>
        get() = _mRegisterComplete

    private val _mLoginComplete: MutableLiveData<State> = MutableLiveData()
    val mLoginComplete: LiveData<State>
        get() = _mLoginComplete

    private val _mFirstNameValid = MutableLiveData<Pair<Boolean, String?>>()
    val mFirstNameValid: LiveData<Pair<Boolean, String?>>
        get() = _mFirstNameValid

    private val _mSecondNameValid = MutableLiveData<Pair<Boolean, String?>>()
    val mSecondNameValid: LiveData<Pair<Boolean, String?>>
        get() = _mSecondNameValid

    private val _mLoginValid = MutableLiveData<Pair<Boolean, String?>>()
    val mLoginValid: LiveData<Pair<Boolean, String?>>
        get() = _mLoginValid

    private val _mPasswordValid = MutableLiveData<Pair<Boolean, String?>>()
    val mPasswordValid: LiveData<Pair<Boolean, String?>>
        get() = _mPasswordValid

    init {
        _mButtonClickability.postValue(mCheckList)

        _mButtonClickability.addSource(_mFirstNameValid) {
            mCheckList[0] = it.first
            _mButtonClickability.postValue(mCheckList)
        }

        _mButtonClickability.addSource(_mSecondNameValid) {
            mCheckList[1] = it.first
            _mButtonClickability.postValue(mCheckList)
        }

        _mButtonClickability.addSource(_mLoginValid) {
            mCheckList[2] = it.first
            _mButtonClickability.postValue(mCheckList)
        }

        _mButtonClickability.addSource(_mPasswordValid) {
            mCheckList[3] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
    }

    @SuppressLint("CheckResult")
    fun registerUser(registerUser: RegisterUserUI) {
        _mRegisterComplete.postValue(State.LOADING)
        userProcessingUseCases.registerUser(registerUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mRegisterComplete.postValue(State.LOADED)
                loginAfterRegister(LoginUserUI(registerUser.login, registerUser.password))
            }, {
                _mRegisterComplete.postValue(State.fail(ResponseHandler.parseException(it)))
            })
    }

    fun validateUserFirstName(userFirstName: String) =
        userProcessingUseCases.validateUserFullName(userFirstName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mFirstNameValid.postValue(Pair(true, ""))
            }, {
                _mFirstNameValid.postValue(Pair(false, it.message))
            })

    fun validateUserLastName(userLastName: String) =
        userProcessingUseCases.validateUserFullName(userLastName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mSecondNameValid.postValue(Pair(true, ""))
            }, {
                _mSecondNameValid.postValue(Pair(false, it.message))
            })

    fun validateUserEmail(userEmail: String) =
        userProcessingUseCases.validateEmail(userEmail)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mLoginValid.postValue(Pair(true, ""))
            },{
                _mLoginValid.postValue(Pair(false, it.message))
            })

    fun validateUserPassword(userPassword: String) =
        userProcessingUseCases.validatePassword(userPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mPasswordValid.postValue(Pair(true, ""))
            },{
                _mPasswordValid.postValue(Pair(false, it.message))
            })

    fun checkUncheck() {
        mCheckList[4] = !mCheckList[4]
        _mButtonClickability.postValue(mCheckList)
    }

    @SuppressLint("CheckResult")
    private fun loginAfterRegister(loginUser: LoginUserUI) {
        _mLoginComplete.postValue(State.LOADING)
        userProcessingUseCases.loginUser(loginUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mLoginComplete.postValue(State.LOADED)
            }, {
                _mLoginComplete.postValue(State.fail(ResponseHandler.parseException(it)))
            })
    }
}