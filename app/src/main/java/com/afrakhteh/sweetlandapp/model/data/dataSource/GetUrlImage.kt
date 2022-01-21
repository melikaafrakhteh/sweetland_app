package com.afrakhteh.sweetlandapp.model.data.dataSource

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.dataSource.Readable
import com.afrakhteh.sweetlandapp.util.toByteArray
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableOnSubscribe
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class GetUrlImage(
    private val context: Context
) : Readable.IO<String, Single<ByteArray>> {

    override fun read(input: String): Single<ByteArray> {
        return getImage(input).map {
            it.toByteArray()
        }
    }

    @SuppressLint("CheckResult")
    private fun getImage(uri: String): Single<Bitmap> {
        return Single.create() { emitter ->
            Glide.with(context)
                .asBitmap()
                .load(uri)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        emitter.onSuccess(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }

    }
}