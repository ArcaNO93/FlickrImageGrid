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
import com.example.ilcarro.utils.Event
import com.example.ilcarro.utils.Mapper
import com.example.ilcarro.utils.ResponseHandler
import com.example.ilcarro.utils.State
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class LetTheCarWorkLocationViewModel @Inject constructor(
    private val mCarProcessingUseCases: CarProcessingUseCasesImpl,
    private val mCarStorageUseCases: CarStorageUseCasesImpl
): ViewModel() {

    private var mCheckList: MutableList<Boolean> = MutableList(3) {
        false
    }

    var mCarInfoLocationChunk =
        Mapper.toAddCarUILocationChunk(mCarStorageUseCases.fetchDataFromRepo())

    private val mDestination = MutableLiveData<Event<Int>>()

    private val _mFetchGeodataProgress = MediatorLiveData<State>()
    val mFetchGeodataProgress: LiveData<State>
        get() = _mFetchGeodataProgress

    private val _mButtonClickability = MediatorLiveData<MutableList<Boolean>>()
    val mButtonClickability: LiveData<MutableList<Boolean>>
        get() = _mButtonClickability

    private val _mCountryValid = MutableLiveData<Pair<Boolean, String?>>()
    val mCountryValid: LiveData<Pair<Boolean, String?>>
        get() = _mCountryValid

    private val _mCityValid = MutableLiveData<Pair<Boolean, String?>>()
    val mCityValid: LiveData<Pair<Boolean, String?>>
        get() = _mCityValid

    private val _mStreetValid = MutableLiveData<Pair<Boolean, String?>>()
    val mStreetValid: LiveData<Pair<Boolean, String?>>
        get() = _mStreetValid

    init {
        _mButtonClickability.postValue(mCheckList)
        _mButtonClickability.apply {
            addSource(_mCountryValid) {
                mCheckList[0] = it.first
                _mButtonClickability.postValue(mCheckList)
            }
            addSource(_mCityValid) {
                mCheckList[1] = it.first
                _mButtonClickability.postValue(mCheckList)
            }
            addSource(_mStreetValid) {
                mCheckList[2] = it.first
                _mButtonClickability.postValue(mCheckList)
            }
        }
    }

    fun getDestination(): LiveData<Event<Int>> = mDestination

    @SuppressLint("CheckResult")
    fun addCarUILocationChunk() {
        mCarStorageUseCases.addCarUILocationChunk(mCarInfoLocationChunk)
        _mFetchGeodataProgress.postValue(State.LOADING)
        mCarStorageUseCases.fetchPlaceID()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mFetchGeodataProgress.postValue(State.LOADED)
                mCarStorageUseCases.addPlaceID(it.results[0].place_id)
                mDestination.postValue(Event(R.id.action_to_car_details_first))
            }, {
                _mFetchGeodataProgress.postValue(State.fail("Error while fetching geodata:\n${ResponseHandler.parseException(it)}"))
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
