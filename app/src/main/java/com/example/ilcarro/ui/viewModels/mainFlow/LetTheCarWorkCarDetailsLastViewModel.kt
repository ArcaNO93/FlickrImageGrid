package com.example.ilcarro.ui.viewModels.mainFlow

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.R
import com.example.ilcarro.business.implementations.CarProcessingUseCasesImpl
import com.example.ilcarro.business.implementations.CarStorageUseCasesImpl
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUICarDetailsLastChunk
import com.example.ilcarro.utils.Event
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

    var mCarInfoLastChunk = AddCarUICarDetailsLastChunk()

    private val _mCarAddingStatus = MutableLiveData<NetworkState>()
    val mCarAddingStatus: LiveData<NetworkState>
        get() = _mCarAddingStatus

    private val mDestination = MutableLiveData<Event<Int>>()

    private val _mButtonClickability = MediatorLiveData<MutableList<Boolean>>()
    val mButtonClickability: LiveData<MutableList<Boolean>>
        get() = _mButtonClickability

    private val _mAboutValid = MutableLiveData<Pair<Boolean, String?>>()
    val mAboutValid: LiveData<Pair<Boolean, String?>>
        get() = _mAboutValid

    private val _mPricePerDayValid = MutableLiveData<Pair<Boolean, String?>>()
    val mPricePerDayValid: LiveData<Pair<Boolean, String?>>
        get() = _mPricePerDayValid

    init {
        _mButtonClickability.postValue(mCheckList)
        _mButtonClickability.addSource(_mAboutValid) {
            mCheckList[0] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(_mPricePerDayValid) {
            mCheckList[1] = it.first
            _mButtonClickability.postValue(mCheckList)
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
            }, {
                _mCarAddingStatus.postValue(NetworkState.fail(ResponseHandler.parseException(it)))
            })
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