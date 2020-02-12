package com.example.ilcarro.ui.viewModels.mainFlow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.R
import com.example.ilcarro.business.implementations.CarProcessingUseCasesImpl
import com.example.ilcarro.business.implementations.CarStorageUseCasesImpl
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.utils.Event
import com.example.ilcarro.utils.Mapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class LetTheCarWorkCarDetailsFirstViewModel @Inject constructor(
    private val mCarProcessingUseCases: CarProcessingUseCasesImpl,
    private val mCarStorageUseCases: CarStorageUseCasesImpl
): ViewModel() {

    private var mCheckList: MutableList<Boolean> = MutableList(6) {
        false
    }

    var mCarInfoFirstChunk = Mapper.toAddCarUICarDetailsFirstChunk(mCarStorageUseCases.fetchDataFromRepo())

    private val mDestination = MutableLiveData<Event<Int>>()

    private val _mButtonClickability = MediatorLiveData<MutableList<Boolean>>()
    val mButtonClickability: LiveData<MutableList<Boolean>>
        get() = _mButtonClickability

    private val _mMakeValid = MutableLiveData<Pair<Boolean, String?>>()
    val mMakeValid: LiveData<Pair<Boolean, String?>>
        get() = _mMakeValid

    private val _mModelValid = MutableLiveData<Pair<Boolean, String?>>()
    val mModelValid: LiveData<Pair<Boolean, String?>>
        get() = _mModelValid

    private val _mYearValid = MutableLiveData<Pair<Boolean, String?>>()
    val mYearValid: LiveData<Pair<Boolean, String?>>
        get() = _mYearValid

    private val _mEngineValid = MutableLiveData<Pair<Boolean, String?>>()
    val mEngineValid: LiveData<Pair<Boolean, String?>>
        get() = _mEngineValid

    private val _mFuelValid = MutableLiveData<Pair<Boolean, String?>>()
    val mFuelValid: LiveData<Pair<Boolean, String?>>
        get() = _mFuelValid

    private val _mTransmissionValid = MutableLiveData<Pair<Boolean, String?>>()
    val mTransmissionValid: LiveData<Pair<Boolean, String?>>
        get() = _mTransmissionValid

    init {
        _mButtonClickability.postValue(mCheckList)
        _mButtonClickability.addSource(_mMakeValid) {
            mCheckList[0] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(_mModelValid) {
            mCheckList[1] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(_mYearValid) {
            mCheckList[2] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(_mEngineValid) {
            mCheckList[3] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(_mFuelValid) {
            mCheckList[4] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(_mTransmissionValid) {
            mCheckList[5] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
    }

    fun addCarUIDetailsFirstChunk() {
        mCarStorageUseCases.addCarUICarDetailsFirstChunk(mCarInfoFirstChunk)
        mDestination.postValue(Event(R.id.action_to_car_details_second))
    }

    fun getDestination(): LiveData<Event<Int>> = mDestination

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