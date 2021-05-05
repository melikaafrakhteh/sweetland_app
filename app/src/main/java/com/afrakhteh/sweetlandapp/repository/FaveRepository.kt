package com.afrakhteh.sweetlandapp.repository

import androidx.lifecycle.LiveData
import com.afrakhteh.sweetlandapp.data.database.FaveDataBase
import com.afrakhteh.sweetlandapp.data.model.FaveModel

class FaveRepository (
        private val db:FaveDataBase
){
    suspend fun insertItem(model: FaveModel):Long = db.faveDao().insertItem(model)

    fun getOneItem(id :Int):FaveModel = db.faveDao().getOneItem(id)

    fun showAllFaves(): LiveData<List<FaveModel>> = db.faveDao().showAllFaves()

    suspend fun deleteAllItems() = db.faveDao().deleteAllItems()

    suspend fun deleteOneItem(id: Int) = db.faveDao().deleteOneItem(id)

}