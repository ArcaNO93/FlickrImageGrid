package com.example.ilcarro.dagger.modules.providers

import com.example.ilcarro.dagger.modules.BaseFragmentModule
import com.example.ilcarro.dagger.modules.mainFlow.MainActivityFragmentsViewModelsModule
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.ui.fragments.mainFlow.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsProvider {

    @ActivityScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideFavoritesFragment(): FavoritesFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideProfileFragment() : ProfileFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideSearchFragment(): HomeFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideTripsFragment(): SearchFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, MainActivityFragmentsViewModelsModule::class])
    abstract fun provideMapsFragment(): MapsFragment
}

