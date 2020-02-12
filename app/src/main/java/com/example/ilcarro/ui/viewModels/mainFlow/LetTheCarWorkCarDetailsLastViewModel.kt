package com.example.ilcarro.ui.viewModels.mainFlow

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.implementations.CarProcessingUseCasesImpl
import com.example.ilcarro.business.implementations.CarStorageUseCasesImpl
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.data.dto.car.ui.addCarUI.AddCarUI
import com.example.ilcarro.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class LetTheCarWorkCarDetailsLastViewModel @Inject constructor(
    private val mCarProcessingUseCases: CarProcessingUseCasesImpl,
    private val mCarStorageUseCases: CarStorageUseCasesImpl
) : ViewModel() {

    lateinit var mImage: String

    private var mCheckList: MutableList<Boolean> = MutableList(2) {
        false
    }

    var mCarInfoLastChunk = AddCarUI()

    private val mDestination = MutableLiveData<Event<Int>>()

    private val _mImageUploadProcess = MutableLiveData<State>()
    val mImageUploadProcess: LiveData<State>
        get() = _mImageUploadProcess

    private val _mImageUrl = MutableLiveData<String>()
    val mImageUrl: LiveData<String>
        get() = _mImageUrl

    private val _mFeatureLiveList = MutableLiveData<MutableList<String>>()
    val mFeatureLiveList: LiveData<MutableList<String>>
        get() = _mFeatureLiveList

    private val _mListValidationError = MutableLiveData<String>()
    val mListValidationError: LiveData<String>
        get() = _mListValidationError

    private val _mCarAddingStatus = MutableLiveData<State>()
    val mCarAddingStatus: LiveData<State>
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
         initUploadImageResult()
        _mSubmitButtonClickability.postValue(mCheckList)
        _mSubmitButtonClickability.apply {
            addSource(_mAboutValid) {
                mCheckList[0] = it.first
                _mSubmitButtonClickability.postValue(mCheckList)
            }
            addSource(_mPricePerDayValid) {
                mCheckList[1] = it.first
                _mSubmitButtonClickability.postValue(mCheckList)
            }
        }
    }

    fun getDestination(): LiveData<Event<Int>> = mDestination

    @SuppressLint("CheckResult")
    fun addCar() {
        _mCarAddingStatus.postValue(State.LOADING)
        mCarProcessingUseCases.addCar(mCarStorageUseCases.fetchDataFromRepo())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mCarAddingStatus.postValue(State.LOADED)
            }, {
                _mCarAddingStatus.postValue(State.fail(ResponseHandler.parseException(it)))
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

    fun addPhoto(image: String) {
        _mImageUploadProcess.postValue(State.LOADING)
        mCarStorageUseCases.uploadImage(image)
    }

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

    @SuppressLint("CheckResult")
    private fun initUploadImageResult() {
        mCarStorageUseCases.fetchImageUploadResult().subscribe({
            mCarInfoLastChunk.images.add(it)
            mImage = it
            _mImageUrl.postValue(it)
            _mImageUploadProcess.postValue(State.LOADED)
        },{
            _mImageUploadProcess.postValue(State.fail(ResponseHandler.parseException(it)))
        })
    }
}