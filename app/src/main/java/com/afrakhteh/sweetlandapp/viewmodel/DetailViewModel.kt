package com.afrakhteh.sweetlandapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.model.data.database.FaveDataBase
import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import kotlinx.coroutines.launch


class DetailViewModel (application: Application)
    : ViewModel() {

   /* fun addToFave(model: FavoriteEntity){
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

    fun showAllFaves(): LiveData<List<FavoriteEntity>> =  FaveDataBase(getApplication()).faveDao().showAllFaves()
*/
}
