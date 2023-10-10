package com.example.noticias.repository

import android.util.Log
import com.example.noticias.repository.mapper.MainServiceMapper
import com.example.noticias.service.MainService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "MainRepository"

class MainRepository(private var retrofitInstance: MainService) {

    suspend fun getNews() =
        withContext(Dispatchers.IO) {
            try {
                val apiKey = "5c444ec9b4e1446db9e2a0f27dd0a14e"
                val response = retrofitInstance.getNews(
                    "bitcoin",
                    apiKey
                )

                if (response.isSuccessful) {
                    val model = response.body()?.let {
                        MainServiceMapper.convertResponseToModel(it)
                    }
                    if (model == null) {
                        MainState.Error
                    } else {
                        MainState.DisplayNews(model)
                    }
                } else {
                    // Lidar com o erro
                    Log.e(TAG, "Error: ${response.code()} - ${response.message()}")
                    MainState.Error
                }

            } catch (e: Exception) {
                Log.e(TAG, "Error: ${e.message}")
                MainState.Error
            }
        }
}