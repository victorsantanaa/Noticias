package com.example.noticias.response

import com.google.gson.annotations.SerializedName

data class NewsListResponse(
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("urlToImage")
    val urlToImage: String,
    @SerializedName("publishedAt")
    val date: String
)
