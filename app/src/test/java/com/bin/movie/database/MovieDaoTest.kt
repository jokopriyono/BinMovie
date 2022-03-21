package com.bin.movie.database

import com.bin.movie.data.local.MovieDao
import com.bin.movie.utils.MockUtils.mockMovieEntity
import com.bin.movie.utils.MockUtils.mockMoviesEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class MovieDaoTest : BinDatabaseTest() {

    private lateinit var movieDao: MovieDao

    @Before
    fun init() {
        movieDao = database.movieDao()
    }

    @Test
    fun insertAndLoadMoviesTest() = runBlocking {
        val mockListMovies = mockMoviesEntity()
        val mockMovie = mockMovieEntity()

        movieDao.insertAllMovies(mockListMovies)

        val loadFromDB = movieDao.getAllMovies()
        assertThat(
            loadFromDB.toString(),
            `is`(mockListMovies.toString())
        )
        assertThat(
            loadFromDB[0].toString(),
            `is`(mockMovie.toString())
        )
        assertThat(
            loadFromDB[0].id.toString(),
            `is`(mockMovie.id.toString())
        )
    }

    @Test
    fun insertAndDeleteMovieTest() = runBlocking {
        val mockListMovies = mockMoviesEntity()

        movieDao.insertAllMovies(mockListMovies)

        var loadFromDB = movieDao.getAllMovies()

        assertThat(
            loadFromDB.size.toString(),
            `is`(mockListMovies.size.toString())
        )

        movieDao.deleteMovie(mockListMovies[0])
        loadFromDB = movieDao.getAllMovies()

        assertThat(
            loadFromDB.size.toString(),
            `is`(0.toString())
        )

    }

}