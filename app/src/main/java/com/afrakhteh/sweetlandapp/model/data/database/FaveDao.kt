package com.afrakhteh.sweetlandapp.model.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity

@Dao
interface FaveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(model: FavoriteEntity)

    @Query(" SELECT * FROM favorite_tb")
    suspend fun showAllFaves(): List<FavoriteEntity>

    @Query(" DELETE FROM favorite_tb")
    suspend fun deleteAllItems()

    @Query(" DELETE FROM favorite_tb WHERE id_col = :id")
    suspend fun deleteOneItem(id: Int)

    @Query("SELECT COUNT (*) FROM favorite_tb WHERE name_col = :title")
    suspend fun isSweetLiked(title: String): Int

}
