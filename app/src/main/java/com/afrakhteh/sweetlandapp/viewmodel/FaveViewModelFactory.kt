package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afrakhteh.sweetlandapp.data.database.FaveDao
import com.afrakhteh.sweetlandapp.repository.FaveRepository

class FaveViewModelFactory(
    private val repository: FaveRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FaveViewModel(repository) as T
    }
}