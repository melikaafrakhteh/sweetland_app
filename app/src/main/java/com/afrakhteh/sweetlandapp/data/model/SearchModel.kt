package com.afrakhteh.sweetlandapp.data.model

import androidx.room.ColumnInfo

data class SearchModel(

        var id: Int,
        val description: String,
        val image: String,
        val name: String,
        val recipe: String,
        val time: String
)
