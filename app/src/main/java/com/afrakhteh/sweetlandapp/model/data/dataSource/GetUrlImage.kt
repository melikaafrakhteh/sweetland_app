package com.afrakhteh.sweetlandapp.model.data.dataSource

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.afrakhteh.sweetlandapp.R
import com.afrakhteh.sweetlandapp.dataSource.Readable
import com.afrakhteh.sweetlandapp.util.toByteArray
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class GetUrlImage (
    private val context: Context
) : Readable.IO<String, Observable<ByteArray>> {

    override fun read(input: String): Observable<ByteArray> {
       return getImage(input).map { it.toByteArray() }
    }

    private fun getImage(uri: String): PublishSubject<Bitmap> {
        val bitMapObservable: PublishSubject<Bitmap> = PublishSubject.create()
        Glide.with(context)
            .asBitmap()
            .load(uri)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitMapObservable.onNext(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {}

            })
        return bitMapObservable
    }
}