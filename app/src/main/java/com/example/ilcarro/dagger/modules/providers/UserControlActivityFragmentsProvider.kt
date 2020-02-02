package com.example.ilcarro.dagger.modules.providers

import com.example.ilcarro.dagger.modules.BaseFragmentModule
import com.example.ilcarro.dagger.modules.userFlow.UserControlActivityFragmentsViewModelsModule
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.ui.fragments.userFlow.GetStartedFragment
import com.example.ilcarro.ui.fragments.userFlow.LogInFragment
import com.example.ilcarro.ui.fragments.userFlow.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserControlActivityFragmentsProvider {

    @FragmentScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, UserControlActivityFragmentsViewModelsModule::class])
    abstract fun provideLogInFragment(): LogInFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, UserControlActivityFragmentsViewModelsModule::class])
    abstract fun provideSignUpFragment(): SignUpFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [BaseFragmentModule::class, UserControlActivityFragmentsViewModelsModule::class])
    abstract fun provideGetStartedFragment(): GetStartedFragment
}