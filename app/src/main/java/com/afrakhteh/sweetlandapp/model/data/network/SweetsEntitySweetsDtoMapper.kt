package com.afrakhteh.sweetlandapp.model.data.network

import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.entities.dto.SweetsDto
import com.afrakhteh.sweetlandapp.util.Mapper
import javax.inject.Inject

class SweetsEntitySweetsDtoMapper @Inject constructor()
    : Mapper<SweetsEntity, SweetsDto> {
    override fun convertFirstObjectToSecond(presentation: SweetsEntity): SweetsDto {
        return SweetsDto(
                description = presentation.description,
                id = presentation.id.toString(),
                image = presentation.image!!,
                name = presentation.name!!,
                recipe = presentation.recipe!!,
                time = presentation.time!!
        )
    }

    override fun convertSecondObjectToFirst(data: SweetsDto): SweetsEntity {
        return SweetsEntity(
                id = data.id!!.toInt(),
                description = data.description ?: "",
                image = data.image,
                name = data.name,
                recipe = data.recipe,
                time = data.time
        )
    }
}