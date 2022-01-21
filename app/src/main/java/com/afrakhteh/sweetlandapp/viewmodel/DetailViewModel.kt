package com.afrakhteh.sweetlandapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afrakhteh.sweetlandapp.model.data.database.FaveDataBase
import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.useCase.favorite.DeleteOneFavoriteUseCase
import com.afrakhteh.sweetlandapp.model.useCase.favorite.InsertFaveUseCase
import com.afrakhteh.sweetlandapp.model.useCase.favorite.IsSweetLikedUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailViewModel @Inject constructor(
    private val insertFaveUseCase: InsertFaveUseCase,
    private val deleteOneFavoriteUseCase: DeleteOneFavoriteUseCase,
    private val isSweetLikedUseCase: IsSweetLikedUseCase
) : ViewModel() {

    private var addJob: Job? = null
    private var deleteJob: Job? = null


    suspend fun isSweetsLiked(title: String): Boolean {
        return isSweetLikedUseCase(title)
    }

    fun addToFavoriteList(sweetsEntity: SweetsEntity) {
        addJob = CoroutineScope(Dispatchers.IO).launch {
            insertFaveUseCase(sweetsEntity)
        }
    }

    fun deleteFromFavorite(model: SweetsEntity) {
        deleteJob = CoroutineScope(Dispatchers.IO).launch {
            deleteOneFavoriteUseCase(model)
        }
    }

    override fun onCleared() {
        super.onCleared()
        addJob?.cancel()
        deleteJob?.cancel()
    }
}
