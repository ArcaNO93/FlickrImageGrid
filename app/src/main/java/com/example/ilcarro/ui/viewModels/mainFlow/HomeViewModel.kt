package com.example.ilcarro.ui.viewModels.mainFlow

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.implementations.GettersUseCasesImpl
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.car.ui.ShowCarUI
import com.example.ilcarro.utils.NetworkState
import com.example.ilcarro.utils.ResponseHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class HomeViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var mGettersUseCases: GettersUseCasesImpl

    private val _mLoadingStatus = MutableLiveData<NetworkState>()
    val mLoadingStatus: LiveData<NetworkState>
        get() = _mLoadingStatus

    private val _mTopCars = MutableLiveData<List<ShowCarUI>>()
    val mShowCars: LiveData<List<ShowCarUI>>
        get() = _mTopCars

    private val _mErrorMessageShown = MutableLiveData<Boolean>()
    val mErrorMessageShown: LiveData<Boolean>
        get() = _mErrorMessageShown

    @SuppressLint("CheckResult")
    fun getTopCars() {
        if(_mErrorMessageShown.value == true)
            _mErrorMessageShown.postValue(false)
        _mLoadingStatus.postValue(NetworkState.LOADING)
        mGettersUseCases.getBestBookedCars().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mLoadingStatus.postValue(NetworkState.LOADED)
                _mTopCars.postValue(it)
            }, {
                _mLoadingStatus.postValue(NetworkState.fail(ResponseHandler.parseException(it)))
                _mErrorMessageShown.postValue(true)
        })
    }
}