package com.afrakhteh.sweetlandapp.di.component

import com.afrakhteh.sweetlandapp.di.module.RepositoryModule
import com.afrakhteh.sweetlandapp.di.scope.RepoScope
import com.afrakhteh.sweetlandapp.model.repository.MainRepository
import dagger.Component

@RepoScope
@Component(
        modules = [RepositoryModule::class],
        dependencies = [
            ApplicationComponent::class,
            NetworkComponent::class]
)
interface MainActivityComponent {
    fun exposeMainRepository(): MainRepository
}