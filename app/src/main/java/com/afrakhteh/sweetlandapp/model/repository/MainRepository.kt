package com.afrakhteh.sweetlandapp.model.repository

import android.graphics.Bitmap
import com.afrakhteh.sweetlandapp.model.entities.dto.ArticleDto
import com.afrakhteh.sweetlandapp.model.entities.dto.SweetsDto
import io.reactivex.Observable
import io.reactivex.Single

interface MainRepository {
    fun getAllSweets(): Single<List<SweetsDto>>
    fun getAllSweetsForSearch(): Single<List<SweetsDto>>
    fun getArticles(): Single<List<ArticleDto>>
    fun getImages(id: String):Observable<ByteArray>
}