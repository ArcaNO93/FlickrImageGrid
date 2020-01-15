package com.example.ilcarro.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ilcarro.dagger.scopes.FragmentScope
import com.example.ilcarro.dagger.scopes.GlobalScope
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
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
