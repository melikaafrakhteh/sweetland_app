package com.afrakhteh.sweetlandapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.afrakhteh.sweetlandapp.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelProvidersModule {

    @Binds
    fun bindViewModelProvides(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}