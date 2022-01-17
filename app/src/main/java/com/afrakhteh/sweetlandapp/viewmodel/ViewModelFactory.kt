package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afrakhteh.sweetlandapp.di.scope.ViewModelScope
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider

@ViewModelScope
class ViewModelFactory @Inject constructor(
     private val viewModel: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      val creator = viewModel[modelClass]
              ?: viewModel.asIterable().firstOrNull(){
                  modelClass.isAssignableFrom(it.key)
              }?.value
              ?:throw IllegalArgumentException("unknown model class $modelClass")
        return try {
            creator.get() as T
        }catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}