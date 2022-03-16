package com.bin.movie.data.remote.upcoming


import com.bin.movie.data.remote.Movie
import com.google.gson.annotations.SerializedName

data class UpcomingResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)