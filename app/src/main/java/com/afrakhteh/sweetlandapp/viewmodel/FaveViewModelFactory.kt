package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afrakhteh.sweetlandapp.data.database.FaveDao

class FaveViewModelFactory(
    private val dao: FaveDao
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FaveViewModel(dao) as T
    }
}