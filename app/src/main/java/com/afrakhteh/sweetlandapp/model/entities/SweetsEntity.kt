package com.afrakhteh.sweetlandapp.model.entities

data class SweetsEntity(
    var id: Int? = null,
    val description: String? = null,
    val image: String? = null,
    val name: String? = null,
    val recipe: String? = null,
    val time: String? = null,
    var isFave: Boolean = false
)