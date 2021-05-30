package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.data.model.FaveModel
import com.afrakhteh.sweetlandapp.repository.FaveRepository

class FaveViewModel(repository: FaveRepository) : ViewModel() {

    val showAllFaves: LiveData<List<FaveModel>> = repository.showAllFaves()

}
