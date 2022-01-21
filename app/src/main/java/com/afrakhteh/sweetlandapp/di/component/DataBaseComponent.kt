package com.afrakhteh.sweetlandapp.di.component

import com.afrakhteh.sweetlandapp.di.module.DataBaseModule
import com.afrakhteh.sweetlandapp.di.scope.DbScope
import com.afrakhteh.sweetlandapp.model.data.database.FaveDao
import dagger.Component

@Component(
    modules = [DataBaseModule::class],
    dependencies = [ApplicationComponent::class]
)
@DbScope
interface DataBaseComponent {
    fun exposeFaveDao(): FaveDao
}