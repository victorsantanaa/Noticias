package com.example.noticias.viewmodel

import com.example.noticias.model.NewsModel

sealed class MainViewState {

    data class Loading(
        val show: Boolean
    ) : MainViewState()

    data class DisplayNews(
        val news: ArrayList<NewsModel>
    ) : MainViewState()

    data object DisplayError : MainViewState()
}
