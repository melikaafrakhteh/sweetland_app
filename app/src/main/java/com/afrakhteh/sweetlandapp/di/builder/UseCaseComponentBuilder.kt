package com.afrakhteh.sweetlandapp.di.builder

import com.afrakhteh.sweetlandapp.di.component.UseCaseComponent
import com.afrakhteh.sweetlandapp.di.component.DaggerUseCaseComponent
import com.afrakhteh.sweetlandapp.di.module.UseCaseModule

object UseCaseComponentBuilder : ComponentBuilder<UseCaseComponent>() {
    override fun provideInstance(): UseCaseComponent {
        return DaggerUseCaseComponent.builder()
                .repositoryComponent(RepositoryComponentBuilder.getInstance())
                .useCaseModule(UseCaseModule())
                .build()
    }
}

