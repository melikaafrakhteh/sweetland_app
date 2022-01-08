package com.afrakhteh.sweetlandapp

import android.app.Application
import com.afrakhteh.sweetlandapp.di.builder.ApplicationComponentBuilder

class App: Application() {
    override fun onCreate() {
        super.onCreate()
       ApplicationComponentBuilder.app = this
    }
}