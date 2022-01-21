package com.afrakhteh.sweetlandapp.model.data.network.mapper

import com.afrakhteh.sweetlandapp.model.entities.ArticleEntity
import com.afrakhteh.sweetlandapp.model.entities.dto.ArticleDto
import com.afrakhteh.sweetlandapp.util.Mapper
import javax.inject.Inject

class ArticleEntityArticleDtoMapper @Inject constructor(): Mapper<ArticleEntity, ArticleDto> {
    override fun convertFirstObjectToSecond(presentation: ArticleEntity): ArticleDto {
        return ArticleDto (
            description = presentation.description,
            id = presentation.id,
            source = presentation.source,
            title = presentation.title,
            image = presentation.image
        )
    }

    override fun convertSecondObjectToFirst(data: ArticleDto): ArticleEntity {
        return ArticleEntity(
            description = data.description,
            id = data.id,
            source = data.source,
            title = data.title,
            image = data.image
        )
    }
}