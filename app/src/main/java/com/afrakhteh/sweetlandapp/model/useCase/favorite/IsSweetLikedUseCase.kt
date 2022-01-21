package com.afrakhteh.sweetlandapp.model.useCase.favorite

import com.afrakhteh.sweetlandapp.di.scope.UseCaseScope
import com.afrakhteh.sweetlandapp.model.repository.db.DbRepository
import javax.inject.Inject

@UseCaseScope
class IsSweetLikedUseCase @Inject constructor(
    private val repository: DbRepository
) {
    suspend operator fun invoke (title: String): Boolean {
        return repository.isSweetLiked(title)
    }
}