package com.afrakhteh.sweetlandapp.util

import java.util.*

interface Mapper<I,O> {
    fun convertFirstObjectToSecond(presentation: I): O
    fun convertSecondObjectToFirst(data: O): I
}