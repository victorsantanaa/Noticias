package com.example.noticias.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Long,
    @SerializedName("articles")
    val newsList: List<NewsListResponse>
)
