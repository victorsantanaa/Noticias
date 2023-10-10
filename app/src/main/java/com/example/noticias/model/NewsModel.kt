package com.example.noticias.model

data class NewsModel (
    val image: String? = "",
    val autor: String = "",
    val date: String = "",
    val description: String = "",
    val title: String = "",
    val url: String = ""
)