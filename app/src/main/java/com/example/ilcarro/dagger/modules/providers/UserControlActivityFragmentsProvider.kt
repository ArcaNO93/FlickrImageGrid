package com.example.ilcarro.dagger.modules.providers

import com.example.ilcarro.dagger.modules.BaseFragmentModule
import com.example.ilcarro.dagger.modules.userFlow.UserControlActivityFragmentsViewModelsModule
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.ui.fragments.userFlow.GetStartedFragment
import com.example.ilcarro.ui.fragments.userFlow.LogInFragment
import com.example.ilcarro.ui.fragments.userFlow.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserControlActivityFragmentsProvider {

    @ActivityScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, UserControlActivityFragmentsViewModelsModule::class])
    abstract fun provideLogInFragment(): LogInFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, UserControlActivityFragmentsViewModelsModule::class])
    abstract fun provideSignUpFragment(): RegisterFragment

    @ActivityScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, UserControlActivityFragmentsViewModelsModule::class])
    abstract fun provideGetStartedFragment(): GetStartedFragment
}