package com.example.noticias.repository

import com.example.noticias.model.NewsModel

sealed class MainState {

    data class DisplayNews(
        val listOfNews: ArrayList<NewsModel>
    ) : MainState()

    object Error : MainState()
}