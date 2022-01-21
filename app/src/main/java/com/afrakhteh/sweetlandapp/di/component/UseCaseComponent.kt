package com.afrakhteh.sweetlandapp.di.component

import com.afrakhteh.sweetlandapp.di.module.MapperModule
import com.afrakhteh.sweetlandapp.di.module.UseCaseModule
import com.afrakhteh.sweetlandapp.di.scope.UseCaseScope
import com.afrakhteh.sweetlandapp.model.entities.ArticleEntity
import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.entities.dto.ArticleDto
import com.afrakhteh.sweetlandapp.model.entities.dto.SweetsDto
import com.afrakhteh.sweetlandapp.model.useCase.article.GetAllArticlesUseCase
import com.afrakhteh.sweetlandapp.model.useCase.favorite.*
import com.afrakhteh.sweetlandapp.model.useCase.sweets.GetAllSweetsUseCase
import com.afrakhteh.sweetlandapp.model.useCase.sweets.SearchSweetsUseCase
import com.afrakhteh.sweetlandapp.util.Mapper
import dagger.Component

@UseCaseScope
@Component(
        modules = [UseCaseModule::class,
                   MapperModule::class],
        dependencies = [RepositoryComponent::class]
)
interface UseCaseComponent {
    fun exposeMapper(): Mapper<SweetsEntity,SweetsDto>
    fun exposeArticleMapper(): Mapper<ArticleEntity, ArticleDto>
    fun exposeFaveMapper(): Mapper<SweetsEntity, FavoriteEntity>

    fun exposeGetAllSweetsUseCase(): GetAllSweetsUseCase
    fun exposeGetAllArticleUseCase(): GetAllArticlesUseCase
    fun exposeSearchSweetsUseCase(): SearchSweetsUseCase

    fun exposeInsertFaveUseCase(): InsertFaveUseCase
    fun exposeDeleteOneFavoriteUseCase(): DeleteOneFavoriteUseCase
    fun exposeShowAllFaveListUseCase(): ShowAllFaveListUseCase
    fun exposeIsSweetLikedUseCase(): IsSweetLikedUseCase
}