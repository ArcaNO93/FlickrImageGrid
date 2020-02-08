package com.example.ilcarro.ui.viewModels.mainFlow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.utils.Event
import javax.inject.Inject

@ActivityScope
class LetTheCarWorkViewModel @Inject constructor(): ViewModel() {
    private val destination = MutableLiveData<Event<Int>>()

    fun getDestination(): LiveData<Event<Int>> = destination

    fun setNewDestination(destinationId: Int) {
        destination.postValue(Event(destinationId))
    }

}
