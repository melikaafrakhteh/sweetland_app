package com.afrakhteh.sweetlandapp.view.main.state

import com.afrakhteh.sweetlandapp.model.entities.ArticleEntity
import com.afrakhteh.sweetlandapp.util.SingleEvent

data class ArticlesState(
    val data : List<ArticleEntity> = emptyList(),
    val errorMessage: SingleEvent<String> ? = null
)
