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
    internal abstract fun provideHostViewModel(favoritesViewModel: FavoritesViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(ProfileViewModel::class)
    internal abstract fun provideProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(HomeViewModel::class)
    internal abstract fun provideSearchViewModel(homeViewModel: HomeViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(SearchViewModel::class)
    internal abstract fun provideTripsViewModel(searchViewModel: SearchViewModel): ViewModel

    @ActivityScope
    @Binds
    @IntoMap
    @ViewModelKeys(MapsViewModel::class)
    internal abstract fun provideMapsViewModel(mapsViewModel: MapsViewModel): ViewModel
}