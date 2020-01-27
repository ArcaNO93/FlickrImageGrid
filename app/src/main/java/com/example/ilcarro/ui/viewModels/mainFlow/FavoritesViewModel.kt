package com.example.ilcarro.ui.viewModels.mainFlow

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.implementations.UserProcessingUseCasesImpl
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.data.dto.user.ui.UpdateUserUI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@FragmentScope
class FavoritesViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var mUserProcessingUseCases: UserProcessingUseCasesImpl

    private val _mUpdateComplete: MutableLiveData<Boolean> = MutableLiveData()
    val mUpdateComplete: LiveData<Boolean>
        get() = _mUpdateComplete

    @SuppressLint("CheckResult")
    fun updateUser(user: UpdateUserUI) =
        mUserProcessingUseCases.updateUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _mUpdateComplete.value = true
            },{
                _mUpdateComplete.value = false
            })
}