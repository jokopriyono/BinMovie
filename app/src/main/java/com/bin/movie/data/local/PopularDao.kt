package com.bin.movie.data.local

import androidx.room.*
import com.bin.movie.data.model.local.PopularEntity
import com.bin.movie.data.model.local.relation.PopularMovies

@Dao
interface PopularDao {

    @Transaction
    @Query("SELECT * FROM popular_movies")
    fun getPopularMovies(): List<PopularMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPopularMovies(popularEntities: List<PopularEntity>)

    @Query("DELETE FROM popular_movies")
    fun deleteAllPopularMovies()

}