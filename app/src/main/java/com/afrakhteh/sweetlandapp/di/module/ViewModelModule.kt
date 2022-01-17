package com.afrakhteh.sweetlandapp.di.module

import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.di.ViewModelKey
import com.afrakhteh.sweetlandapp.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}