package com.example.ilcarro.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ilcarro.dagger.scopes.ActivityScope
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
@ActivityScope
class ViewModelFactory @Inject constructor (
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        val viewModel = viewModels[modelClass] ?: viewModels.asIterable().firstOrNull{ modelClass.isAssignableFrom(it.key) }?.value ?:
        throw IllegalArgumentException("unknown model class: $modelClass")
        return try {
            viewModel.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
