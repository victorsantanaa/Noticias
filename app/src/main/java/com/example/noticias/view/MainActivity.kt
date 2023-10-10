package com.example.noticias.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.noticias.databinding.ActivityMainBinding
import com.example.noticias.model.NewsModel
import com.example.noticias.repository.MainRepository
import com.example.noticias.service.MainService
import com.example.noticias.service.RetrofitInstance
import com.example.noticias.viewmodel.MainViewModel
import com.example.noticias.viewmodel.MainViewModelFactory
import com.example.noticias.viewmodel.MainViewState

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitInstance = RetrofitInstance
            .getRetrofitInstance()
            .create(MainService::class.java)

        val repository = MainRepository(
            retrofitInstance
        )

        viewModel = ViewModelProvider(this,
            MainViewModelFactory(repository)).get(MainViewModel::class.java)

        viewModel.getNewsFromNetwork()

        initObservables()
    }

    private fun initObservables() {
        viewModel.newsViewState.observe(this@MainActivity) {
            when (it) {
                MainViewState.DisplayError -> displayError()
                is MainViewState.DisplayNews -> setupListOfNews(it.news)
                is MainViewState.Loading -> displayLoading()
            }
        }
    }

    private fun setupListOfNews(news: ArrayList<NewsModel>) {
        setupRecyclerView(news)
        binding.scrollView.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
    }

    private fun setupRecyclerView(news: ArrayList<NewsModel>) {
        val adapter = MainAdapter(news, Glide.with(this))
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun displayError() {
        binding.scrollView.visibility = View.GONE
        binding.loading.visibility = View.GONE
    }

    private fun displayLoading() {
        binding.scrollView.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
    }
}