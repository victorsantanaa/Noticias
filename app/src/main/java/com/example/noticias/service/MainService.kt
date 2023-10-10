package com.example.noticias.service

import com.example.noticias.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainService {
    //5c444ec9b4e1446db9e2a0f27dd0a14e

    @GET("/v2/everything")
    suspend fun getNews(
        @Query("q") subject: String,
        @Query("apiKey") apiKey: String
    ) : Response<NewsResponse>
}