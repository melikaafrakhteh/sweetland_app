package com.afrakhteh.sweetlandapp.di.module

import android.content.Context
import androidx.room.Room
import com.afrakhteh.sweetlandapp.constants.Strings
import com.afrakhteh.sweetlandapp.di.scope.DbScope
import com.afrakhteh.sweetlandapp.model.data.database.FaveDao
import com.afrakhteh.sweetlandapp.model.data.database.FaveDataBase
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    @Provides
    @DbScope
    fun provideDataBase (context: Context): FaveDataBase{
        return Room.databaseBuilder(
            context,
            FaveDataBase::class.java,
            Strings.DATABASE_NAME
        ).build()
    }

    @Provides
    @DbScope
    fun provideFaveDao(room: FaveDataBase): FaveDao {
        return room.faveDao()
    }

}