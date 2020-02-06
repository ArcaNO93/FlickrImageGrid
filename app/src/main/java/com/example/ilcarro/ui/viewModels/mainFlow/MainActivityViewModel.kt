package com.example.ilcarro.ui.viewModels.mainFlow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.implementations.UserProcessingUseCasesImpl
import com.example.ilcarro.dagger.scopes.ActivityScope
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@ActivityScope
class MainActivityViewModel @Inject constructor(
    private val mUserProcessingUseCases: UserProcessingUseCasesImpl
) : ViewModel() {

    private val _mIsLogged = MutableLiveData<Boolean>()
    val mIsLogged: LiveData<Boolean>
        get() = _mIsLogged

    fun getIsLogged(): Disposable = mUserProcessingUseCases.getIsLogged().subscribe {
        _mIsLogged.postValue(it)
    }
}