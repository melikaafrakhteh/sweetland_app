package com.afrakhteh.sweetlandapp.model.repository.db

import com.afrakhteh.sweetlandapp.di.scope.RepoScope
import com.afrakhteh.sweetlandapp.model.data.database.FaveDao
import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity
import javax.inject.Inject

@RepoScope
class DbRepositoryImpl @Inject constructor(
    private val dao: FaveDao
): DbRepository {
    override suspend fun insertFave(model: FavoriteEntity) {
        dao.insertItem(model)
    }

    override suspend fun showAllFaves(): List<FavoriteEntity> {
        return dao.showAllFaves()
    }

    override suspend fun deleteOneItem(model: FavoriteEntity) {
       dao.deleteOneItem(model.id)
    }

    override suspend fun isSweetLiked(title: String): Boolean {
        return dao.isSweetLiked(title) != 0
    }
}