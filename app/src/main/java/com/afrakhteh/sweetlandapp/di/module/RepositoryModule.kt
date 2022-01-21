package com.afrakhteh.sweetlandapp.di.module

import android.content.Context
import com.afrakhteh.sweetlandapp.di.scope.RepoScope
import com.afrakhteh.sweetlandapp.model.data.database.FaveDao
import com.afrakhteh.sweetlandapp.model.data.network.SweetsApi
import com.afrakhteh.sweetlandapp.model.repository.db.DbRepository
import com.afrakhteh.sweetlandapp.model.repository.db.DbRepositoryImpl
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @RepoScope
    fun provideMainRepository(api: SweetsApi, context: Context): MainRepository {
        return MainRepositoryImpl(api, context)
    }

    @Provides
    @RepoScope
    fun provideDbRepository(dao: FaveDao): DbRepository {
        return DbRepositoryImpl(dao)
    }
}