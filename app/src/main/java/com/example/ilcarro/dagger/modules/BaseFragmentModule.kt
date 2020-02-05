package com.example.ilcarro.dagger.modules

import androidx.lifecycle.ViewModelProvider
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class BaseFragmentModule {

    @Binds
    @ActivityScope
    internal abstract fun provideViewModelsFactory(ViewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}