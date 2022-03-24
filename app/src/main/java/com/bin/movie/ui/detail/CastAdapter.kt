package com.bin.movie.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bin.movie.databinding.ItemCastBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = 8

    inner class ViewHolder(var binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root)
}