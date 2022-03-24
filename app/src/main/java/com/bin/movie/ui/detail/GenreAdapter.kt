package com.bin.movie.ui.detail

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bin.movie.R
import com.bin.movie.databinding.ItemGenreBinding

class GenreAdapter(private val genres: List<String>) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genres[position]

        when (position) {
            genres.size - 1 -> {
                holder.binding.txtGenre.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }
            else -> {
                holder.binding.txtGenre.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_oval,
                    0
                )
                holder.binding.txtGenre.compoundDrawablePadding =
                    (7 * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
            }
        }

        holder.binding.txtGenre.text = genre
    }

    override fun getItemCount(): Int = 3

    inner class ViewHolder(var binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root)
}