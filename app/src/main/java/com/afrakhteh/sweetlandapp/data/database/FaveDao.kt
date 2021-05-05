package com.afrakhteh.sweetlandapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.afrakhteh.sweetlandapp.data.model.FaveModel
import com.afrakhteh.sweetlandapp.data.model.SweetsModel

@Dao
interface FaveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg fave: FaveModel): List<Long>

    @Query(" SELECT * FROM fave_table WHERE uuid = :id")
    suspend fun getOneItem(id :Int):FaveModel

    @Query(" DELETE FROM FAVE_TABLE")
    suspend fun deleteAllItems()

    @Delete
    suspend fun deleteOneItem(faveModel: FaveModel)
}