package com.afrakhteh.sweetlandapp.di.module

import com.afrakhteh.sweetlandapp.model.data.network.ArticleEntityArticleDtoMapper
import com.afrakhteh.sweetlandapp.model.data.network.SweetsEntitySweetsDtoMapper
import com.afrakhteh.sweetlandapp.model.entities.ArticleEntity
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.entities.dto.ArticleDto
import com.afrakhteh.sweetlandapp.model.entities.dto.SweetsDto
import com.afrakhteh.sweetlandapp.util.Mapper
import dagger.Binds
import dagger.Module

@Module
interface MapperModule {

    @Binds
    fun bindDataPresentationModels(
            sweets: SweetsEntitySweetsDtoMapper
    ): Mapper<SweetsEntity, SweetsDto>

    @Binds
    fun bindArticleMapper (
        articleEntityArticleDtoMapper: ArticleEntityArticleDtoMapper
    ): Mapper<ArticleEntity, ArticleDto>
}