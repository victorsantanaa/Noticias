package com.example.noticias.repository.mapper

import com.example.noticias.model.NewsModel
import com.example.noticias.response.NewsResponse

object MainServiceMapper {

    fun convertResponseToModel(response: NewsResponse): ArrayList<NewsModel> {

        val listOfNews = arrayListOf<NewsModel>()

        response.newsList.forEach { item ->

            listOfNews.add(
                NewsModel(
                    item.urlToImage,
                    item.author,
                    item.date,
                    item.description,
                    item.title,
                    item.url
                )
            )
        }

        return listOfNews

    }
}