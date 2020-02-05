package com.example.ilcarro.dagger.modules.userFlow

import androidx.lifecycle.ViewModel
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.dagger.scopes.ViewModelKeys
import com.example.ilcarro.ui.viewModels.userFlow.GetStartedViewModel
import com.example.ilcarro.ui.viewModels.userFlow.LogInViewModel
import com.example.ilcarro.ui.viewModels.userFlow.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserControlActivityFragmentsViewModelsModule {

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(LogInViewModel::class)
    internal abstract fun provideLogInViewModel(logInViewModel: LogInViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(RegisterViewModel::class)
    internal abstract fun provideSignUpViewModel(signUpViewModel: RegisterViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(GetStartedViewModel::class)
    internal abstract fun provideGetStartedViewModel(getStartedViewModel: GetStartedViewModel): ViewModel
}