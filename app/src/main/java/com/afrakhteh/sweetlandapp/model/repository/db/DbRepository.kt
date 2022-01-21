package com.afrakhteh.sweetlandapp.model.repository.db

import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity

interface DbRepository {
    suspend fun insertFave(model: FavoriteEntity)
    suspend fun showAllFaves(): List<FavoriteEntity>
    suspend fun deleteOneItem(model: FavoriteEntity)
    suspend fun isSweetLiked(title: String): Boolean
}