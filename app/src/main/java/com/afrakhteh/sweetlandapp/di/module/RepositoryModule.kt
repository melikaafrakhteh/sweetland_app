package com.afrakhteh.sweetlandapp.di.module

import android.content.Context
import com.afrakhteh.sweetlandapp.di.scope.RepoScope
import com.afrakhteh.sweetlandapp.model.data.network.SweetsApi
import com.afrakhteh.sweetlandapp.model.repository.MainRepository
import com.afrakhteh.sweetlandapp.model.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @RepoScope
    fun provideMainRepository(api: SweetsApi, context: Context): MainRepository {
        return MainRepositoryImpl(api, context)
    }
}