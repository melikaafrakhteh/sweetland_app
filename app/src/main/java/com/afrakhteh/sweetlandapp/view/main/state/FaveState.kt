package com.afrakhteh.sweetlandapp.view.main.state


import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.util.SingleEvent

data class FaveState (
    val data : List<SweetsEntity> = emptyList(),
    val errorMessage: SingleEvent<String>? = null
)