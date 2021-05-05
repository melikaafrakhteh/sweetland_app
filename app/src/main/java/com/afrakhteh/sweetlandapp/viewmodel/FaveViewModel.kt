package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.data.database.FaveDao
import com.afrakhteh.sweetlandapp.data.model.FaveModel

public class FaveViewModel(dao: FaveDao) : ViewModel() {

    val showAllFaves: LiveData<List<FaveModel>>

    init {
        showAllFaves = dao.showAllFaves()
    }


}
