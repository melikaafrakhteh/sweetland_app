package com.afrakhteh.sweetlandapp.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.afrakhteh.sweetlandapp.constants.Numerals
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

fun ByteArray.toBitmap(): Bitmap? {
    return BitmapFactory.decodeByteArray(
        this,
        0,
        this.size
    )
}

fun Bitmap.toByteArray(): ByteArray {
    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 90, stream)
    return stream.toByteArray()
}

fun Bitmap.resize(maxSize: Int = Numerals.MAX_BITMAP_SIZE): Bitmap {
    var width = this.width
    var height = this.height
    val ratio = width.toFloat() / height.toFloat()
    if (ratio > 1) {
        width = maxSize
        height = (width / ratio).toInt()
    } else {
        height = maxSize
        width = (height * ratio).toInt()
    }
    return Bitmap.createScaledBitmap(this, width, height, true)
}