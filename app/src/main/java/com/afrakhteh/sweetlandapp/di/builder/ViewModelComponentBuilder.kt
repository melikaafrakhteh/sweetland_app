package com.afrakhteh.sweetlandapp.di.builder

import com.afrakhteh.sweetlandapp.di.component.ViewModelComponent
import com.afrakhteh.sweetlandapp.di.component.DaggerViewModelComponent

object ViewModelComponentBuilder: ComponentBuilder<ViewModelComponent>() {
    override fun provideInstance(): ViewModelComponent {
        return DaggerViewModelComponent.builder()
            .useCaseComponent(UseCaseComponentBuilder.getInstance())
            .mainActivityComponent(MainActivityComponentBuilder.getInstance())
            .build()
    }
}