package com.example.ilcarro.ui.viewModels.userFlow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.UserProcessingUseCasesImpl
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.user.ui.RegisterUserUI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class RegisterViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var userProcessingUseCases: UserProcessingUseCasesImpl

    private val _mRegisterComplete: MutableLiveData<Boolean> = MutableLiveData()
    val mRegisterComplete: LiveData<Boolean>
        get() = _mRegisterComplete

    fun registerUser(registerUser: RegisterUserUI) =
        userProcessingUseCases.registerUser(registerUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mRegisterComplete.value = true
            },{
                _mRegisterComplete.value = false
            })
}