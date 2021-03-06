package com.afrakhteh.sweetlandapp.model.repository.network

import android.content.Context
import com.afrakhteh.sweetlandapp.di.scope.RepoScope
import com.afrakhteh.sweetlandapp.model.data.dataSource.GetUrlImage
import com.afrakhteh.sweetlandapp.model.data.network.SweetsApi
import com.afrakhteh.sweetlandapp.model.entities.dto.ArticleDto
import com.afrakhteh.sweetlandapp.model.entities.dto.SweetsDto
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

@RepoScope
class MainRepositoryImpl @Inject constructor(
        private val api: SweetsApi,
        private val context: Context
): MainRepository {

    override fun getAllSweets(): Single<List<SweetsDto>> {
        return api.getSweets()
    }

    override fun getArticles(): Single<List<ArticleDto>> {
        return api.getArticles()
    }

    override fun getImages(id: String): Single<ByteArray> {
        return GetUrlImage(context).read(id)
    }
}