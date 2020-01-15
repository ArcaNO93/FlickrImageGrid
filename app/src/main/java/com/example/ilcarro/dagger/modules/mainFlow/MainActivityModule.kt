package com.example.ilcarro.dagger.modules.mainFlow

import androidx.lifecycle.ViewModel
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.dagger.scopes.ViewModelKeys
import com.example.ilcarro.ui.viewModels.mainFlow.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(MainActivityViewModel::class)
    internal abstract fun provideMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}

