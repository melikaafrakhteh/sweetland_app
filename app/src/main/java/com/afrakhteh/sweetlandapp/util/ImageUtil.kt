package com.afrakhteh.sweetlandapp.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.afrakhteh.sweetlandapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun getProgressDrawable(
        context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

    fun ImageView.loadingImage(
            uri:String?,circularProgressDrawable: CircularProgressDrawable){
        val action = RequestOptions()
                .placeholder(circularProgressDrawable)
                .error(R.drawable.error)

        Glide.with(context).setDefaultRequestOptions(action).load(uri).into(this)
    }


