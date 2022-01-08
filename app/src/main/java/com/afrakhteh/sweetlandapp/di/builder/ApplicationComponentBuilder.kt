package com.afrakhteh.sweetlandapp.di.builder

import android.app.Application
import com.afrakhteh.sweetlandapp.di.component.ApplicationComponent
import com.afrakhteh.sweetlandapp.di.component.DaggerApplicationComponent

object ApplicationComponentBuilder: ComponentBuilder<ApplicationComponent>() {
    lateinit var app: Application

    override fun provideInstance(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .bindApplication(app)
            .build()
    }
}