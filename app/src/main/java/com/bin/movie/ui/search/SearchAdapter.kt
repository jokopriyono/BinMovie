package com.bin.movie.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bin.movie.data.model.local.MovieEntity
import com.bin.movie.databinding.ItemSearchBinding
import com.bumptech.glide.Glide

class SearchAdapter(
    var populars: MutableList<MovieEntity>,
    private val onItemClick: (MovieEntity) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = populars[position]
        holder.binding.txtDesc.text = movie.overview
        holder.binding.txtJudul.text = movie.title
        holder.binding.itemSearch.setOnClickListener {
            onItemClick(movie)
        }
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
            .into(holder.binding.imgItemSearch)
    }

    override fun getItemCount() = populars.size

}