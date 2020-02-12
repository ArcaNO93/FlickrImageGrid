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
class LetTheCarWorkCarDetailsSecondViewModel @Inject constructor(
    private val mCarProcessingUseCases: CarProcessingUseCasesImpl,
    private val mCarStorageUseCases: CarStorageUseCasesImpl
) : ViewModel() {

    private var mCheckList: MutableList<Boolean> = MutableList(8) {
        false
    }

    var mCarInfoSecondChunk = Mapper.toAddCarUICarDetailsSecondChunk(mCarStorageUseCases.fetchDataFromRepo())

    private val destination = MutableLiveData<Event<Int>>()

    private val _mButtonClickability = MediatorLiveData<MutableList<Boolean>>()
    val mButtonClickability: LiveData<MutableList<Boolean>>
        get() = _mButtonClickability

    private val _mWDValid = MutableLiveData<Pair<Boolean, String?>>()
    val mWDValid: LiveData<Pair<Boolean, String?>>
        get() = _mWDValid

    private val _mHorsePowerValid = MutableLiveData<Pair<Boolean, String?>>()
    val mHorsePowerValid: LiveData<Pair<Boolean, String?>>
        get() = _mHorsePowerValid

    private val _mTorqueValid = MutableLiveData<Pair<Boolean, String?>>()
    val mTorqueValid: LiveData<Pair<Boolean, String?>>
        get() = _mTorqueValid

    private val _mFuelConsumptionValid = MutableLiveData<Pair<Boolean, String?>>()
    val mFuelConsumptionValid: LiveData<Pair<Boolean, String?>>
        get() = _mFuelConsumptionValid

    private val _mDistanceIncludedValid = MutableLiveData<Pair<Boolean, String?>>()
    val mDistanceIncludedValid: LiveData<Pair<Boolean, String?>>
        get() = _mDistanceIncludedValid

    private val _mDoorsValid = MutableLiveData<Pair<Boolean, String?>>()
    val mDoorsValid: LiveData<Pair<Boolean, String?>>
        get() = _mDoorsValid

    private val _mSeatsValid = MutableLiveData<Pair<Boolean, String?>>()
    val mSeatsValid: LiveData<Pair<Boolean, String?>>
        get() = _mSeatsValid

    private val _mClassValid = MutableLiveData<Pair<Boolean, String?>>()
    val mClassValid: LiveData<Pair<Boolean, String?>>
        get() = _mClassValid

    init {
        _mButtonClickability.postValue(mCheckList)
        _mButtonClickability.addSource(_mWDValid) {
            mCheckList[0] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(_mHorsePowerValid) {
            mCheckList[1] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(_mTorqueValid) {
            mCheckList[2] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(_mFuelConsumptionValid) {
            mCheckList[3] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(_mDistanceIncludedValid) {
            mCheckList[4] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(_mDoorsValid) {
            mCheckList[5] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(_mSeatsValid) {
            mCheckList[6] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
        _mButtonClickability.addSource(mClassValid) {
            mCheckList[7] = it.first
            _mButtonClickability.postValue(mCheckList)
        }
    }

    fun getDestination(): LiveData<Event<Int>> = destination

    fun addCarUIDetailsSecondChunk() {
        mCarStorageUseCases.addCarUIDetailsSecondChunk(mCarInfoSecondChunk)
        destination.postValue(Event(R.id.action_to_car_details_last))
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