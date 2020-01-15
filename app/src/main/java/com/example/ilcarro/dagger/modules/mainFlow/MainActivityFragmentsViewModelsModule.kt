package com.example.ilcarro.dagger.modules.mainFlow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.dagger.scopes.ViewModelKeys
import com.example.ilcarro.ui.viewModels.mainFlow.HostViewModel
import com.example.ilcarro.ui.viewModels.mainFlow.ProfileViewModel
import com.example.ilcarro.ui.viewModels.mainFlow.SearchViewModel
import com.example.ilcarro.ui.viewModels.mainFlow.TripsViewModel
import com.example.ilcarro.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityFragmentsViewModelsModule {

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKeys(HostViewModel::class)
    internal abstract fun provideHostViewModel(hostViewModel: HostViewModel): ViewModel

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKeys(ProfileViewModel::class)
    internal abstract fun provideProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKeys(SearchViewModel::class)
    internal abstract fun provideSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @FragmentScope
    @Binds
    @IntoMap
    @ViewModelKeys(TripsViewModel::class)
    internal abstract fun provideTripsViewModel(tripsViewModel: TripsViewModel): ViewModel
}