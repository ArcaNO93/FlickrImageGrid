package com.example.ilcarro.dagger.modules.providers

import com.example.ilcarro.dagger.modules.BaseFragmentModule
import com.example.ilcarro.dagger.modules.mainFlow.MainActivityFragmentsViewModelsModule
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.ui.fragments.mainFlow.HostFragment
import com.example.ilcarro.ui.fragments.mainFlow.ProfileFragment
import com.example.ilcarro.ui.fragments.mainFlow.SearchFragment
import com.example.ilcarro.ui.fragments.mainFlow.TripsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideHostFragment(): HostFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideProfileFragment() : ProfileFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideSearchFragment(): SearchFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideTripsFragment(): TripsFragment
}

