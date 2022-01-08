package com.afrakhteh.sweetlandapp.di.component

import android.app.Application
import android.content.Context
import com.afrakhteh.sweetlandapp.App
import com.afrakhteh.sweetlandapp.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class]
)
interface ApplicationComponent: AndroidInjector<App>  {
    fun exposeContext(): Context

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindApplication(app: Application): Builder

        fun build() : ApplicationComponent
    }
}