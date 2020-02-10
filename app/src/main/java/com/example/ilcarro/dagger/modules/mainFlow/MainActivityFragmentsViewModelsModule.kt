package com.example.ilcarro.dagger.modules.mainFlow

import androidx.lifecycle.ViewModel
import com.example.ilcarro.dagger.scopes.ActivityScope
import com.example.ilcarro.dagger.scopes.ViewModelKeys
import com.example.ilcarro.ui.viewModels.mainFlow.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityFragmentsViewModelsModule {

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(FavoritesViewModel::class)
    internal abstract fun provideHostViewModel(viewModel: FavoritesViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(ProfileViewModel::class)
    internal abstract fun provideProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(HomeViewModel::class)
    internal abstract fun provideSearchViewModel(viewModel: HomeViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(SearchViewModel::class)
    internal abstract fun provideTripsViewModel(viewModel: SearchViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(MapsViewModel::class)
    internal abstract fun provideMapsViewModel(viewModel: MapsViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(LetTheCarWorkLocationViewModel::class)
    internal abstract fun provideLetTheCarWorkViewModel(viewModel: LetTheCarWorkLocationViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(LetTheCarWorkCarDetailsFirstViewModel::class)
    internal abstract fun provideLetTheCarWorkCarDetailsFirstViewModell(viewModel: LetTheCarWorkCarDetailsFirstViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(LetTheCarWorkCarDetailsSecondViewModel::class)
    internal abstract fun provideLetTheCarWorkCarDetailsSecondViewModel(viewModel: LetTheCarWorkCarDetailsSecondViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(LetTheCarWorkCarDetailsLastViewModel::class)
    internal abstract fun provideLetTheCarWorkCarDetailsLastViewModel(viewModel: LetTheCarWorkCarDetailsLastViewModel): ViewModel
}