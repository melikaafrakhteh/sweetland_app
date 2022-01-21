package com.afrakhteh.sweetlandapp.model.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.afrakhteh.sweetlandapp.constants.Strings
import com.afrakhteh.sweetlandapp.di.scope.DbScope
import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity


@Database(
    entities = [FavoriteEntity::class],
    version = Strings.DATABASE_VERSION,
    exportSchema = false
)
@DbScope
abstract class FaveDataBase : RoomDatabase() {
    abstract fun faveDao(): FaveDao
}