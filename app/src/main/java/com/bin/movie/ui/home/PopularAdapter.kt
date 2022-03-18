package com.bin.movie.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bin.movie.data.model.local.MovieEntity
import com.bin.movie.databinding.ItemPopularBinding
import com.bumptech.glide.Glide

class PopularAdapter(
    var populars: MutableList<MovieEntity>,
    private val onItemClick: (MovieEntity) -> Unit
) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPopularBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = populars[position]
        holder.binding.imgPopular.setOnClickListener {
            onItemClick(movie)
        }
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(holder.binding.imgPopular)
    }

    override fun getItemCount() = populars.size

}