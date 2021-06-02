package com.afrakhteh.sweetlandapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.afrakhteh.sweetlandapp.data.database.FaveDataBase
import com.afrakhteh.sweetlandapp.data.model.FaveModel
import com.afrakhteh.sweetlandapp.data.model.SweetsModel
import kotlinx.coroutines.launch

class DetailViewModel (application: Application)
    : BaseViewModel(application) {

    val detail = MutableLiveData<SweetsModel>()
    private val isFave = MutableLiveData<Int>()

    fun fetchData(id: Int, description: String, image: String, name: String, recipe: String, time: String) {
        val sweet = SweetsModel(id, description, image, name, recipe, time)
        detail.value = sweet
    }

    fun addToFave(model: FaveModel){
        launch {
            FaveDataBase(getApplication()).faveDao().insertItem(model)
            isFave.value = 1
            //    Toast.makeText(getApplication(),"add",Toast.LENGTH_LONG).show()
        }
    }
    fun removeFave(id:Int){
        launch {
            FaveDataBase(getApplication()).faveDao().deleteOneItem(id)
            isFave.value = 0
            //      Toast.makeText(getApplication(),"delete",Toast.LENGTH_LONG).show()
        }
    }

}