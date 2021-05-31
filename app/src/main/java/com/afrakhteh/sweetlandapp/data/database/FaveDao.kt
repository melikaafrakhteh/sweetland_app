package com.afrakhteh.sweetlandapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afrakhteh.sweetlandapp.data.model.FaveModel

@Dao
interface FaveDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(model: FaveModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItem(model: FaveModel)

    @Query(" SELECT * FROM fave_table WHERE uuid = :id")
    fun getOneItem(id: Int): FaveModel

    @Query(" SELECT * FROM fave_table WHERE uuid = :id")
    fun getLiveDataOneItem(id: Int) : LiveData<FaveModel>

    @Query(" SELECT * FROM fave_table")
    fun showAllFaves(): LiveData<List<FaveModel>>

    @Query(" DELETE FROM fave_table")
    suspend fun deleteAllItems()

    @Query(" DELETE FROM fave_table WHERE id_col = :id")
    suspend fun deleteOneItem(id: Int)
}
