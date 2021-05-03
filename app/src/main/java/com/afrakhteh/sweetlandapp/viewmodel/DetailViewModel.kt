package com.afrakhteh.sweetlandapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.data.model.SweetsModel

class DetailViewModel : ViewModel() {

    val detail = MutableLiveData<SweetsModel>()

    fun fetchData(id: Int, description: String, image: String, name: String, recipe: String, time: String) {
        val sweet = SweetsModel(id, description, image, name, recipe, time)
        detail.value = sweet
    }
}