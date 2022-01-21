package com.afrakhteh.sweetlandapp.di.builder

import com.afrakhteh.sweetlandapp.di.component.DaggerRepositoryComponent
import com.afrakhteh.sweetlandapp.di.component.RepositoryComponent
import com.afrakhteh.sweetlandapp.di.module.RepositoryModule

object RepositoryComponentBuilder: ComponentBuilder<RepositoryComponent>() {
    override fun provideInstance(): RepositoryComponent {
        return DaggerRepositoryComponent.builder()
                .applicationComponent(ApplicationComponentBuilder.getInstance())
                .networkComponent(NetworkComponentBuilder.getInstance())
                .dataBaseComponent(DataBaseComponentBuilder.getInstance())
                .repositoryModule(RepositoryModule())
                .build()
    }
}