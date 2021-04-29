package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.data.model.SweetsModel

class HomeViewModel : ViewModel() {

    private val sweetlist = MutableLiveData<List<SweetsModel>>()
    private val loadingError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()


    fun refreshList(){

    }

}