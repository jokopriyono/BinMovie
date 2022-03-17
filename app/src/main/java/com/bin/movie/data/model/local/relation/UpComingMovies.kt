package com.bin.movie.data.model.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.bin.movie.data.model.local.MovieEntity
import com.bin.movie.data.model.local.UpComingEntity


data class UpComingMovies(
    @Embedded
    val upComingEntity: UpComingEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "id"
    )
    val movies: List<MovieEntity>
)