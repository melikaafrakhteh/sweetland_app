package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.data.model.FaveModel

class FaveViewModel : ViewModel() {

     var listOfFave = MutableLiveData<List<FaveModel>>()

    fun addToFave(){

    }
}