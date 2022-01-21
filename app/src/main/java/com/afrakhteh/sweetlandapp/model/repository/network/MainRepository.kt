package com.afrakhteh.sweetlandapp.model.repository.network

import com.afrakhteh.sweetlandapp.model.entities.dto.ArticleDto
import com.afrakhteh.sweetlandapp.model.entities.dto.SweetsDto
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxjava3.core.Completable

interface MainRepository {
    fun getAllSweets(): Single<List<SweetsDto>>
    fun getArticles(): Single<List<ArticleDto>>
    fun getImages(id: String): Single<ByteArray>
}

