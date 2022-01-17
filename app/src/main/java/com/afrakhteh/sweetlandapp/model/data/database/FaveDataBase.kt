package com.afrakhteh.sweetlandapp.model.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity
import com.afrakhteh.sweetlandapp.util.Constants


@Database(
        entities = [FavoriteEntity::class],
        version = Constants.DATABASE_VERSION,
         exportSchema = false
)
abstract class FaveDataBase : RoomDatabase() {

    abstract fun faveDao(): FaveDao

    companion object {
        @Volatile
        private var INSTANCE: FaveDataBase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) =
                INSTANCE ?: synchronized(LOCK) {
                    INSTANCE ?: buidDB(context).also {
                        INSTANCE = it
                    }
                }


        private fun buidDB(context: Context) = Room.databaseBuilder(context,
                FaveDataBase::class.java,
                Constants.DATABASE_NAME)
                .build()

    }

}