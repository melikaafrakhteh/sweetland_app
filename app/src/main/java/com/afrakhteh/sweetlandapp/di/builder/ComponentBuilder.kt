package com.afrakhteh.sweetlandapp.di.builder

import android.content.Context

abstract class ComponentBuilder<C> {
    private var instance: C ? = null

    abstract fun provideInstance(): C

    fun getInstance(): C {
        if (instance == null)
            instance = provideInstance()

        return requireNotNull(instance)
    }

}