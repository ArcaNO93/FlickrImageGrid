package com.example.ilcarro.dagger.components

import com.example.ilcarro.dagger.modules.providers.ActivityProvider
import com.example.ilcarro.dagger.modules.AppModule
import com.example.ilcarro.dagger.modules.providers.MainActivityFragmentsProvider
import com.example.ilcarro.dagger.modules.providers.UserControlActivityFragmentsProvider
import com.example.ilcarro.dagger.scopes.GlobalScope
import com.example.ilcarro.utils.ComponentProvider
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component (modules = [
    AppModule::class,
    AndroidSupportInjectionModule::class,
    ActivityProvider::class,
    MainActivityFragmentsProvider::class,
    UserControlActivityFragmentsProvider::class
])

@GlobalScope
interface AppComponent : AndroidInjector<ComponentProvider> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<ComponentProvider>
}