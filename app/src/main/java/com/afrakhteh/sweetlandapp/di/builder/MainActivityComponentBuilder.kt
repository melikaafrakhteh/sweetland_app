package com.afrakhteh.sweetlandapp.di.builder

import com.afrakhteh.sweetlandapp.di.component.DaggerMainActivityComponent
import com.afrakhteh.sweetlandapp.di.component.MainActivityComponent
import com.afrakhteh.sweetlandapp.di.module.RepositoryModule

object MainActivityComponentBuilder: ComponentBuilder<MainActivityComponent>() {
    override fun provideInstance(): MainActivityComponent {
        return DaggerMainActivityComponent.builder()
                .applicationComponent(ApplicationComponentBuilder.getInstance())
                .networkComponent(NetworkComponentBuilder.getInstance())
                .repositoryModule(RepositoryModule())
                .build()
    }
}