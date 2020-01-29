package com.example.ilcarro.ui.viewModels.mainFlow

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.implementations.GettersUseCasesImpl
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.car.ui.TopCarUI
import com.example.ilcarro.utils.NetworkState
import com.example.ilcarro.utils.ResponseHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class HomeViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var mGettersUseCases: GettersUseCasesImpl

    private val _mLoadingStatus = MutableLiveData<NetworkState>()
    val mLoadingStatus: LiveData<NetworkState>
        get() = _mLoadingStatus

    private val _mTopCars = MutableLiveData<List<TopCarUI>>()
    val mTopCars: LiveData<List<TopCarUI>>
        get() = _mTopCars

    @SuppressLint("CheckResult")
    fun getTopCars() {
        _mLoadingStatus.postValue(NetworkState.LOADING)
        mGettersUseCases.getBestBookedCars().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mLoadingStatus.postValue(NetworkState.LOADED)
                _mTopCars.postValue(it)
            }, {
                Log.d("tag", it.message)
                _mLoadingStatus.postValue(NetworkState.fail(ResponseHandler.parseException(it)))
        })
    }
}