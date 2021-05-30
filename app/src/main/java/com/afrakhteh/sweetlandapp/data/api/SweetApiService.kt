package com.afrakhteh.sweetlandapp.data.api

import com.afrakhteh.sweetlandapp.data.model.SearchModel
import com.afrakhteh.sweetlandapp.data.model.SweetsModel
import com.afrakhteh.sweetlandapp.util.UrlLinks
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SweetApiService {
    private val api = Retrofit.Builder()
            .baseUrl(UrlLinks.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SweetsApi::class.java)

    fun getSweets(): Single<List<SweetsModel>> {
        return api.getSweets()
    }

    fun search(): Single<List<SearchModel>>{
        return api.search()
    }
}