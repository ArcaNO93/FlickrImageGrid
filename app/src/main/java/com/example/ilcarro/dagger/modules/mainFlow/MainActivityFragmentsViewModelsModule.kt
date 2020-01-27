package com.example.ilcarro.dagger.modules.mainFlow

import androidx.lifecycle.ViewModel
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.dagger.scopes.ViewModelKeys
import com.example.ilcarro.ui.viewModels.mainFlow.FavoritesViewModel
import com.example.ilcarro.ui.viewModels.mainFlow.ProfileViewModel
import com.example.ilcarro.ui.viewModels.mainFlow.HomeViewModel
import com.example.ilcarro.ui.viewModels.mainFlow.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityFragmentsViewModelsModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKeys(FavoritesViewModel::class)
    internal abstract fun provideHostViewModel(favoritesViewModel: FavoritesViewModel): ViewModel

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKeys(ProfileViewModel::class)
    internal abstract fun provideProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKeys(HomeViewModel::class)
    internal abstract fun provideSearchViewModel(homeViewModel: HomeViewModel): ViewModel

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKeys(SearchViewModel::class)
    internal abstract fun provideTripsViewModel(searchViewModel: SearchViewModel): ViewModel
}