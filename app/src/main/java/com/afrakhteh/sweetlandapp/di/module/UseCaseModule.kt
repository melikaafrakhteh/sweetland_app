package com.afrakhteh.sweetlandapp.di.module

import com.afrakhteh.sweetlandapp.di.scope.UseCaseScope
import com.afrakhteh.sweetlandapp.model.entities.ArticleEntity
import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.entities.dto.ArticleDto
import com.afrakhteh.sweetlandapp.model.entities.dto.SweetsDto
import com.afrakhteh.sweetlandapp.model.repository.db.DbRepository
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import com.afrakhteh.sweetlandapp.model.useCase.article.GetAllArticlesUseCase
import com.afrakhteh.sweetlandapp.model.useCase.favorite.*
import com.afrakhteh.sweetlandapp.model.useCase.sweets.GetAllSweetsUseCase
import com.afrakhteh.sweetlandapp.model.useCase.sweets.SearchSweetsUseCase
import com.afrakhteh.sweetlandapp.util.Mapper
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    @UseCaseScope
    fun provideGetAllSweetsUseCase(
        repository: MainRepository,
        mapper: Mapper<SweetsEntity, SweetsDto>
    ): GetAllSweetsUseCase {
        return GetAllSweetsUseCase(repository, mapper)
    }

    @Provides
    @UseCaseScope
    fun provideGetAllArticlesUseCase(
        repository: MainRepository,
        mapper: Mapper<ArticleEntity, ArticleDto>
    ): GetAllArticlesUseCase {
        return GetAllArticlesUseCase(repository, mapper)
    }

    @Provides
    @UseCaseScope
    fun provideSearchSweetsUseCase(
        repository: MainRepository,
        mapper: Mapper<SweetsEntity, SweetsDto>
    ): SearchSweetsUseCase {
        return SearchSweetsUseCase(repository, mapper)
    }

    @Provides
    @UseCaseScope
    fun provideInsertFaveUseCase(
        repository: DbRepository,
        mapper: Mapper<SweetsEntity, FavoriteEntity>
    ): InsertFaveUseCase {
        return InsertFaveUseCase(repository, mapper)
    }

    @Provides
    @UseCaseScope
    fun provideDeleteOneFavoriteUseCase(
        repository: DbRepository,
        mapper: Mapper<SweetsEntity, FavoriteEntity>
    ): DeleteOneFavoriteUseCase {
        return DeleteOneFavoriteUseCase(repository, mapper)
    }

    @Provides
    @UseCaseScope
    fun provideShowAllFaveListUseCase(
        repository: DbRepository,
        mapper: Mapper<SweetsEntity, FavoriteEntity>
    ): ShowAllFaveListUseCase {
        return ShowAllFaveListUseCase(repository, mapper)
    }

    @Provides
    @UseCaseScope
    fun provideIsSweetLikedUseCase(
        repository: DbRepository
    ): IsSweetLikedUseCase {
        return IsSweetLikedUseCase(repository)
    }

}