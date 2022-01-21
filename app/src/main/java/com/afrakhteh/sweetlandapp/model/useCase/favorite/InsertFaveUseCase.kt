package com.afrakhteh.sweetlandapp.model.useCase.favorite

import com.afrakhteh.sweetlandapp.di.scope.UseCaseScope
import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.repository.db.DbRepository
import com.afrakhteh.sweetlandapp.util.Mapper
import javax.inject.Inject

@UseCaseScope
class InsertFaveUseCase @Inject constructor(
    private val repository: DbRepository,
    private val mapper: Mapper<SweetsEntity, FavoriteEntity>
){
    suspend operator fun invoke(sweets: SweetsEntity) {
        sweets.isFave = true
        repository.insertFave(mapper.convertFirstObjectToSecond(sweets))
    }
}