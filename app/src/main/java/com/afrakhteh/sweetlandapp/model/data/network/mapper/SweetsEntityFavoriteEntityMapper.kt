package com.afrakhteh.sweetlandapp.model.data.network.mapper

import com.afrakhteh.sweetlandapp.model.entities.FavoriteEntity
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.util.Mapper
import javax.inject.Inject

class SweetsEntityFavoriteEntityMapper @Inject constructor(): Mapper<SweetsEntity, FavoriteEntity> {
    override fun convertFirstObjectToSecond(presentation: SweetsEntity): FavoriteEntity {
        return FavoriteEntity(
            id = presentation.id!!,
            description = presentation.description!!,
            image = presentation.image!!,
            name = presentation.name!!,
            recipe = presentation.recipe!!,
            time = presentation.time!!,
        )
    }

    override fun convertSecondObjectToFirst(data: FavoriteEntity): SweetsEntity {
        return SweetsEntity(
            id = data.id,
            description = data.description,
            image = data.image,
            name = data.name,
            recipe = data.recipe,
            time = data.time
        )
    }
}