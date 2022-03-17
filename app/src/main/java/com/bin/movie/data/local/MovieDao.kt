package com.bin.movie.data.local

import androidx.room.*
import com.bin.movie.data.model.local.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovies(movieEntities: List<MovieEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movieEntity: MovieEntity)

    @Delete
    fun deleteMovie(movieEntity: MovieEntity)

}