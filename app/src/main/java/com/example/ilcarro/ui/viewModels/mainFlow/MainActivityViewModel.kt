package com.example.ilcarro.ui.viewModels.mainFlow

import androidx.lifecycle.ViewModel
import com.example.ilcarro.business.implementations.UserProcessingUseCasesImpl
import com.example.ilcarro.dagger.scopes.ActivityScope
import javax.inject.Inject

@ActivityScope
class MainActivityViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var mUserProcessingUseCases: UserProcessingUseCasesImpl

    fun isLogged() = mUserProcessingUseCases.getIsLogged()
}