package com.afrakhteh.sweetlandapp.di.builder

import com.afrakhteh.sweetlandapp.di.component.DaggerNetworkComponent
import com.afrakhteh.sweetlandapp.di.component.NetworkComponent
import com.afrakhteh.sweetlandapp.di.module.NetworkModule

object NetworkComponentBuilder : ComponentBuilder<NetworkComponent>() {
    override fun provideInstance(): NetworkComponent {
        return DaggerNetworkComponent.builder()
                .networkModule(NetworkModule())
                .build()
    }
}