package com.example.ilcarro.ui.viewModels.mainFlow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.implementations.UserProcessingUseCasesImpl
import com.example.ilcarro.dagger.scopes.ActivityScope
import javax.inject.Inject

@ActivityScope
class MainActivityViewModel @Inject constructor(
    private val mUserProcessingUseCases: UserProcessingUseCasesImpl
) : ViewModel() {

    private val _mLogOut = MutableLiveData<Boolean>()
    val mLogOut: LiveData<Boolean>
        get() = _mLogOut

    private val _mIsLogged = MutableLiveData<Boolean>()
    val mIsLogged: LiveData<Boolean>
        get() = _mIsLogged

    fun subscribeToIsLoggedChange() = mUserProcessingUseCases.getIsLogged().subscribe {
        _mIsLogged.postValue(it)
    }

    fun logOut() {
        mUserProcessingUseCases.logOut()
        _mLogOut.postValue(true)
    }

    fun closeAlertDialog() {
        _mLogOut.postValue(false)
    }
}
