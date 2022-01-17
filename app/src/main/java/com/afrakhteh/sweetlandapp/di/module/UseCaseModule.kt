package com.afrakhteh.sweetlandapp.di.module

import com.afrakhteh.sweetlandapp.di.scope.UseCaseScope
import com.afrakhteh.sweetlandapp.model.data.network.ArticleEntityArticleDtoMapper
import com.afrakhteh.sweetlandapp.model.entities.ArticleEntity
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.entities.dto.ArticleDto
import com.afrakhteh.sweetlandapp.model.entities.dto.SweetsDto
import com.afrakhteh.sweetlandapp.model.repository.MainRepository
import com.afrakhteh.sweetlandapp.model.useCase.article.GetAllArticlesUseCase
import com.afrakhteh.sweetlandapp.model.useCase.sweets.GetAllSweetsUseCase
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
    ): GetAllSweetsUseCase{
        return GetAllSweetsUseCase(repository, mapper)
    }
    @Provides
    @UseCaseScope
    fun provideGetAllArticlesUseCase(
        repository: MainRepository,
        mapper: Mapper<ArticleEntity,ArticleDto>
    ): GetAllArticlesUseCase {
        return GetAllArticlesUseCase(repository, mapper)
    }
}