package com.bin.movie.data.local

import androidx.room.*
import com.bin.movie.data.model.local.UpComingEntity
import com.bin.movie.data.model.local.relation.PopularMovies
import com.bin.movie.data.model.local.relation.UpComingMovies

@Dao
interface UpComingDao {

    @Transaction
    @Query("SELECT * FROM up_coming_movies")
    fun getUpComingMovies(): List<UpComingMovies>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUpComingMovies(upComingEntities: List<UpComingEntity>)

    @Query("DELETE FROM up_coming_movies")
    fun deleteAllUpComingMovies()

}