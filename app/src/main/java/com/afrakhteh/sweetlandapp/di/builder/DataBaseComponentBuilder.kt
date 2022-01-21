package com.afrakhteh.sweetlandapp.di.builder

import com.afrakhteh.sweetlandapp.di.component.DaggerDataBaseComponent
import com.afrakhteh.sweetlandapp.di.component.DataBaseComponent
import com.afrakhteh.sweetlandapp.di.module.DataBaseModule

object DataBaseComponentBuilder : ComponentBuilder<DataBaseComponent>(){
    override fun provideInstance(): DataBaseComponent {
        return DaggerDataBaseComponent.builder()
            .applicationComponent(ApplicationComponentBuilder.getInstance())
            .dataBaseModule(DataBaseModule())
            .build()
    }
}