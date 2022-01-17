package com.afrakhteh.sweetlandapp.model.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity

@Dao
interface FaveDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(model: FavoriteEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItem(model: FavoriteEntity)

    @Query(" SELECT * FROM favourite_tb WHERE uuid = :id")
    fun getOneItem(id: Int): FavoriteEntity

    @Query(" SELECT * FROM favourite_tb WHERE uuid = :id")
    fun getLiveDataOneItem(id: Int) : LiveData<FavoriteEntity>

    @Query(" SELECT * FROM favourite_tb")
    fun showAllFaves(): LiveData<List<FavoriteEntity>>

    @Query(" DELETE FROM favourite_tb")
    suspend fun deleteAllItems()

    @Query(" DELETE FROM favourite_tb WHERE id_col = :id")
    suspend fun deleteOneItem(id: Int)


}
