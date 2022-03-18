package com.bin.movie.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bin.movie.databinding.ItemPopularBinding

class UpcomingAdapter(
    var populars: List<String>,
    private val onItemClick: () -> Unit
) : RecyclerView.Adapter<UpcomingAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPopularBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount() = populars.size

}