package com.afrakhteh.sweetlandapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.afrakhteh.sweetlandapp.data.database.FaveDataBase
import com.afrakhteh.sweetlandapp.data.model.FaveModel
import com.afrakhteh.sweetlandapp.data.model.SweetsModel
import kotlinx.coroutines.launch

class DetailViewModel (application: Application)
    : BaseViewModel(application) {

    val detail = MutableLiveData<SweetsModel>()

    fun fetchData(id: Int, description: String, image: String, name: String, recipe: String, time: String) {
        val sweet = SweetsModel(id, description, image, name, recipe, time)
        detail.value = sweet
    }
    fun addToFave(model: FaveModel){
        launch {
            FaveDataBase(getApplication()).faveDao().insertItem(model)
            Toast.makeText(getApplication(),"add",Toast.LENGTH_LONG).show()
        }
    }
    fun removeFave(id:Int){
        launch {
              FaveDataBase(getApplication()).faveDao().deleteOneItem(id)
             Toast.makeText(getApplication(),"delete",Toast.LENGTH_LONG).show()
        }
    }

    fun showAllFaves(): LiveData<List<FaveModel>> =  FaveDataBase(getApplication()).faveDao().showAllFaves()

}