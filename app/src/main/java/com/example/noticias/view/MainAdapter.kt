package com.example.noticias.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.noticias.databinding.LayoutNewsItemListBinding
import com.example.noticias.model.NewsModel

class MainAdapter(
    private val itens: List<NewsModel>,
    private val glide: RequestManager
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = LayoutNewsItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun getItemCount() = itens.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = itens[position]
        holder.bind(item)
    }

    inner class MainViewHolder(val binding: LayoutNewsItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind (item: NewsModel) {
            binding.textAutorName.text = item.autor
            binding.textDate.text = item.date
            binding.textTitleNews.text = item.title
            binding.textBodyNews.text = item.description

            glide.load(item.image).into(binding.imageNews)
        }

    }
}