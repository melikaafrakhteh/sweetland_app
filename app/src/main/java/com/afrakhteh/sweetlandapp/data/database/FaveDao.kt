package com.afrakhteh.sweetlandapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afrakhteh.sweetlandapp.data.model.FaveModel
import com.afrakhteh.sweetlandapp.data.model.SweetsModel

interface FaveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg fave: FaveModel): LiveData<Long>

    @Query( " SELECT * FROM fave_table")
    suspend fun getAllSweets():LiveData<List<FaveModel>>

    @Query(" SELECT * FROM fave_table WHERE uuid = :id")
    suspend fun getOneItem(id :Int):FaveModel

    @Query(" DELETE FROM FAVE_TABLE")
    suspend fun deleteAllItems()

    @Delete
    suspend fun deleteOneItem(faveModel: FaveModel)
}