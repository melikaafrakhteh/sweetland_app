package com.afrakhteh.sweetlandapp.di.component

import com.afrakhteh.sweetlandapp.di.module.RepositoryModule
import com.afrakhteh.sweetlandapp.di.scope.RepoScope
import com.afrakhteh.sweetlandapp.model.repository.db.DbRepository
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import dagger.Component

@RepoScope
@Component(
        modules = [RepositoryModule::class],
        dependencies = [
            ApplicationComponent::class,
            NetworkComponent::class,
            DataBaseComponent::class]
)
interface RepositoryComponent {
    fun exposeMainRepository(): MainRepository
    fun exposeDbRepository(): DbRepository
}