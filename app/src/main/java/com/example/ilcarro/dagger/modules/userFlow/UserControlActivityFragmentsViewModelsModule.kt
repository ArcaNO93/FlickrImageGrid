package com.example.ilcarro.dagger.modules.userFlow

import androidx.lifecycle.ViewModel
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.dagger.scopes.ViewModelKeys
import com.example.ilcarro.ui.viewModels.userFlow.LogInAndSignUpViewModel
import com.example.ilcarro.ui.viewModels.userFlow.LogInViewModel
import com.example.ilcarro.ui.viewModels.userFlow.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserControlActivityFragmentsViewModelsModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKeys(LogInAndSignUpViewModel::class)
    internal abstract fun provideLogInAndSignUpViewModel(logInAndSignUpViewModel: LogInAndSignUpViewModel): ViewModel

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKeys(LogInViewModel::class)
    internal abstract fun provideLogInViewModel(logInViewModel: LogInViewModel): ViewModel

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKeys(RegisterViewModel::class)
    internal abstract fun provideSignUpViewModel(signUpViewModel: RegisterViewModel): ViewModel
}