package com.afrakhteh.sweetlandapp.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun providesContext(app: Application): Context {
        return app
    }
}