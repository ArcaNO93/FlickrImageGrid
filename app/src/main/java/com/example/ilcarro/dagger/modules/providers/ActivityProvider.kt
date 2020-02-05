package com.example.ilcarro.dagger.modules.providers

import com.example.ilcarro.dagger.modules.BaseActivityModule
import com.example.ilcarro.dagger.modules.mainFlow.MainActivityModule
import com.example.ilcarro.dagger.modules.userFlow.UserControlActivityModule
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.ui.activities.MainActivity
import com.example.ilcarro.ui.activities.UserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityProvider {

    @ActivityScope
    @ContributesAndroidInjector(modules = [BaseActivityModule::class, MainActivityModule::class])
    abstract fun provideMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [BaseActivityModule::class, UserControlActivityModule::class])
    abstract fun provideUserControlActivity(): UserActivity
}