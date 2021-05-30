package com.afrakhteh.sweetlandapp.data.api

import com.afrakhteh.sweetlandapp.data.model.SearchModel
import com.afrakhteh.sweetlandapp.data.model.SweetsModel
import com.afrakhteh.sweetlandapp.util.UrlLinks
import io.reactivex.Single
import retrofit2.http.GET

interface SweetsApi {

    @GET(UrlLinks.ENDPOINT_URL)
    fun getSweets(): Single<List<SweetsModel>>

    @GET(UrlLinks.ENDPOINT_URL)
    fun search(): Single<List<SearchModel>>
}