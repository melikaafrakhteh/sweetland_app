package com.afrakhteh.sweetlandapp.di.component

import com.afrakhteh.sweetlandapp.di.module.NetworkModule
import com.afrakhteh.sweetlandapp.model.data.network.SweetsApi
import dagger.Component

@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun exposeApi(): SweetsApi
}