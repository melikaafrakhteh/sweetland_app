package com.afrakhteh.sweetlandapp.model.data.network

import com.afrakhteh.sweetlandapp.constants.Strings
import com.afrakhteh.sweetlandapp.model.entities.dto.ArticleDto
import com.afrakhteh.sweetlandapp.model.entities.dto.SweetsDto
import io.reactivex.Single
import retrofit2.http.GET

interface SweetsApi {

    @GET(Strings.ENDPOINT_URL)
    fun getSweets(): Single<List<SweetsDto>>

    @GET(Strings.ENDPOINT_ARTICLE_URL)
    fun getArticles(): Single<List<ArticleDto>>

}