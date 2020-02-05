package com.example.ilcarro.ui.viewModels.userFlow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.utils.Event
import javax.inject.Inject

@ActivityScope
class GetStartedViewModel @Inject constructor() : ViewModel() {
    private val _mNavigation = MutableLiveData<Event<Int>>()
    val mNavigation: LiveData<Event<Int>>
        get() = _mNavigation

    fun setNewDestination(destinationId: Int) {
        _mNavigation.value = Event(destinationId)
    }
}
