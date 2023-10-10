package com.example.noticias.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noticias.repository.MainState
import com.example.noticias.model.NewsModel
import com.example.noticias.repository.MainRepository
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    private val newsState = MutableLiveData<MainViewState>()
    val newsViewState: MutableLiveData<MainViewState> = newsState

    fun getNewsFromNetwork() {
        try {
            viewModelScope.launch {
                try {
                    newsViewState.value = MainViewState.Loading(true)

                    val news: MainState = repository.getNews()

                    Log.i(TAG, "response = $news")
                    print("$TAG response = $news")

                    when (news) {
                        is MainState.DisplayNews -> displayNews(news.listOfNews)
                        MainState.Error -> displayError()
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error within coroutine: ${e.message}", e)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error launching coroutine: ${e.message}", e)
        }
    }

    private fun displayError() {
        newsState.value = MainViewState.DisplayError
    }

    private fun displayNews(listOfNews: ArrayList<NewsModel>) {
        newsState.value = MainViewState.DisplayNews(listOfNews)
    }

}