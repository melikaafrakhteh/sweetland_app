package com.afrakhteh.sweetlandapp.view.main.state

import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.util.SingleEvent

data class SearchState (
    val listOfSweets: MutableList<SweetsEntity> = mutableListOf(),
    val loading: Boolean = false,
    val error: SingleEvent<String>? = null
)