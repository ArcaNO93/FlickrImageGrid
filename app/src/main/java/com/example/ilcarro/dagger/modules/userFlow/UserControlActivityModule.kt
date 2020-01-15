package com.example.ilcarro.dagger.modules.userFlow

import androidx.lifecycle.ViewModel
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.dagger.scopes.ViewModelKeys
import com.example.ilcarro.ui.viewModels.userFlow.UserControlActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserControlActivityModule {

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(UserControlActivityViewModel::class)
    internal abstract fun provideUserControlActivityViewModel(userControlActivityViewModel: UserControlActivityViewModel): ViewModel
}
