package com.example.ilcarro.ui.viewModels.mainFlow

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.R
import com.example.ilcarro.business.implementations.CarProcessingUseCasesImpl
import com.example.ilcarro.business.implementations.CarStorageUseCasesImpl
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUICarDetailsLastChunk
import com.example.ilcarro.data.dto.general.Features
import com.example.ilcarro.utils.Event
import com.example.ilcarro.utils.Mapper
import com.example.ilcarro.utils.NetworkState
import com.example.ilcarro.utils.ResponseHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class LetTheCarWorkCarDetailsLastViewModel @Inject constructor(
    private val mCarProcessingUseCases: CarProcessingUseCasesImpl,
    private val mCarStorageUseCases: CarStorageUseCasesImpl
) : ViewModel() {

    private var mCheckList: MutableList<Boolean> = MutableList(2) {
        false
    }

    var mCarInfoLastChunk = Mapper.toAddCarUICarDetailsLastChunk(mCarStorageUseCases.getAddCarUI())

    private val mDestination = MutableLiveData<Event<Int>>()

    private val _mFeatureLiveList = MutableLiveData<MutableList<String>>()
    val mFeatureLiveList: LiveData<MutableList<String>>
        get() = _mFeatureLiveList

    private val _mListValidationError = MutableLiveData<String>()
    val mListValidationError: LiveData<String>
        get() = _mListValidationError

    private val _mCarAddingStatus = MutableLiveData<NetworkState>()
    val mCarAddingStatus: LiveData<NetworkState>
        get() = _mCarAddingStatus

    private val _mSubmitButtonClickability = MediatorLiveData<MutableList<Boolean>>()
    val mSubmitButtonClickability: LiveData<MutableList<Boolean>>
        get() = _mSubmitButtonClickability

    private val _mAboutValid = MutableLiveData<Pair<Boolean, String?>>()
    val mAboutValid: LiveData<Pair<Boolean, String?>>
        get() = _mAboutValid

    private val _mPricePerDayValid = MutableLiveData<Pair<Boolean, String?>>()
    val mPricePerDayValid: LiveData<Pair<Boolean, String?>>
        get() = _mPricePerDayValid

    init {
        _mSubmitButtonClickability.postValue(mCheckList)
        _mSubmitButtonClickability.addSource(_mAboutValid) {
            mCheckList[0] = it.first
            _mSubmitButtonClickability.postValue(mCheckList)
        }
        _mSubmitButtonClickability.addSource(_mPricePerDayValid) {
            mCheckList[1] = it.first
            _mSubmitButtonClickability.postValue(mCheckList)
        }
    }

    fun getDestination(): LiveData<Event<Int>> = mDestination

    @SuppressLint("CheckResult")
    fun addCar() {
        mCarStorageUseCases.addCarUIDetailsLastChunk(mCarInfoLastChunk)
        _mCarAddingStatus.postValue(NetworkState.LOADING)
        mCarProcessingUseCases.addCar(mCarStorageUseCases.getAddCarUI())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mCarAddingStatus.postValue(NetworkState.LOADED)
                mDestination.postValue(Event(R.id.profileFragment))
                mCarStorageUseCases.clearRepo()
            }, {
                _mCarAddingStatus.postValue(NetworkState.fail(ResponseHandler.parseException(it)))
            })
    }

    @SuppressLint("CheckResult")
    fun addFeature(feature: String) {
        mCarStorageUseCases.addFeature(feature).subscribe({
            _mFeatureLiveList.postValue(it)
        },{
            _mListValidationError.postValue(it.message)
        })
    }

    @SuppressLint("CheckResult")
    fun removeFeature(feature: String) {
        _mFeatureLiveList.postValue(mCarStorageUseCases.removeFeature(feature))
    }

    fun validateIfEmpty(string: String, liveData: MutableLiveData<Pair<Boolean, String?>>) =
        mCarProcessingUseCases.validateIfEmpty(string)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveData.postValue(Pair(true, ""))
            }, {
                liveData.postValue(Pair(false, it.message))
            })
}