package com.afrakhteh.sweetlandapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.afrakhteh.sweetlandapp.data.database.FaveDataBase
import com.afrakhteh.sweetlandapp.data.model.FaveModel

class FaveViewModel(application: Application) : BaseViewModel(application) {

    private val db: FaveDataBase = FaveDataBase.invoke(application)
   // val showAllFaves: LiveData<List<FaveModel>> = repository.showAllFaves()
   fun showAllFaves(): LiveData<List<FaveModel>> = db.faveDao().showAllFaves()

}