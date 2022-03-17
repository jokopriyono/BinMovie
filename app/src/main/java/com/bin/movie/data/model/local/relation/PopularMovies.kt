package com.bin.movie.data.model.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.bin.movie.data.model.local.MovieEntity
import com.bin.movie.data.model.local.PopularEntity
import com.bin.movie.data.model.local.UpComingEntity


data class PopularMovies(
    @Embedded
    val popularEntity: PopularEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "id"
    )
    val movies: List<MovieEntity>
)