package com.afrakhteh.sweetlandapp.model.useCase.article

import com.afrakhteh.sweetlandapp.di.scope.UseCaseScope
import com.afrakhteh.sweetlandapp.model.entities.ArticleEntity
import com.afrakhteh.sweetlandapp.model.entities.dto.ArticleDto
import com.afrakhteh.sweetlandapp.model.repository.MainRepository
import com.afrakhteh.sweetlandapp.util.Mapper
import com.afrakhteh.sweetlandapp.util.NetworkResponse
import io.reactivex.Observable
import javax.inject.Inject

@UseCaseScope
class GetAllArticlesUseCase @Inject constructor(
    private val repository: MainRepository,
    private val mapper: Mapper<ArticleEntity, ArticleDto>
) {
    operator fun invoke(): Observable<NetworkResponse<ArticleEntity>> {
        return repository.getArticles().toObservable()
            .map { list ->
                NetworkResponse.Success(
                    list.map { dto ->
                        mapper.convertSecondObjectToFirst(dto)
                    }
                ) as NetworkResponse<ArticleEntity>
            }.onErrorReturn {
                NetworkResponse.Error<ArticleEntity>(message = it.toString())
            }
    }
}