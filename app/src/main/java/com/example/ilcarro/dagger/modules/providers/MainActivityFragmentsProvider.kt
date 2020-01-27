package com.example.ilcarro.dagger.modules.providers

import com.example.ilcarro.dagger.modules.BaseFragmentModule
import com.example.ilcarro.dagger.modules.mainFlow.MainActivityFragmentsViewModelsModule
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.ui.fragments.mainFlow.FavoritesFragment
import com.example.ilcarro.ui.fragments.mainFlow.ProfileFragment
import com.example.ilcarro.ui.fragments.mainFlow.HomeFragment
import com.example.ilcarro.ui.fragments.mainFlow.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideHostFragment(): FavoritesFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideProfileFragment() : ProfileFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideSearchFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideTripsFragment(): SearchFragment
}

