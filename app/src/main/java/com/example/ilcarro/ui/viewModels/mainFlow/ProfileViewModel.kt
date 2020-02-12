package com.example.ilcarro.ui.viewModels.mainFlow

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.implementations.UserProcessingUseCasesImpl
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.user.ui.UserUI
import com.example.ilcarro.utils.Event
import com.example.ilcarro.utils.State
import com.example.ilcarro.utils.ResponseHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class ProfileViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var mUserProcessingUseCases: UserProcessingUseCasesImpl

    private val destination = MutableLiveData<Event<Int>>()

    private val _mUserData = MutableLiveData<UserUI>()
    val mUserData: LiveData<UserUI>
        get() = _mUserData

    private val _mUserDataLoadingStatus = MutableLiveData<State>()
    val mUserDataLoadingStatus: LiveData<State>
        get() = _mUserDataLoadingStatus

    private val _mErrorMessageShown = MutableLiveData<Boolean>()
    val mErrorMessageShown: LiveData<Boolean>
        get() = _mErrorMessageShown

    fun getDestination(): LiveData<Event<Int>> = destination

    fun setNewDestination(destinationId: Int) {
        destination.postValue(Event(destinationId))
    }

    @SuppressLint("CheckResult")
    fun getUserData() {
        if(_mErrorMessageShown.value == true)
            _mErrorMessageShown.postValue(false)
        _mUserDataLoadingStatus.postValue(State.LOADING)
        mUserProcessingUseCases.getUserData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mUserDataLoadingStatus.postValue(State.LOADED)
                _mUserData.postValue(it)
            }, {
                _mUserDataLoadingStatus.postValue(State.fail(ResponseHandler.parseException(it)))
                _mErrorMessageShown.postValue(true)
            })
    }
}