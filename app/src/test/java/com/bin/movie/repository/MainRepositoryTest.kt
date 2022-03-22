package com.bin.movie.repository

import android.content.Context
import app.cash.turbine.test
import com.bin.movie.MainCoroutinesRule
import com.bin.movie.data.local.MovieDao
import com.bin.movie.data.remote.ApiService
import com.bin.movie.data.remote.NetworkStateManagerImpl
import com.bin.movie.utils.MockUtils.mockPopularMoviesResponse
import com.nhaarman.mockitokotlin2.*
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
class MainRepositoryTest {

    private lateinit var repository: MainRepository
    private val apiService: ApiService = mock()
    private val movieDao: MovieDao = mock()
    private var context: Context = org.mockito.Mockito.mock(Context::class.java)

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @Before
    fun setup() {
        val networkStateManager = NetworkStateManagerImpl(context)
        repository = MainRepository(apiService, movieDao, Dispatchers.IO, networkStateManager)
    }

    @Test
    fun fetchPopularMoviesFromNetworkTest() = runBlocking {

        val mockPopularResponse = mockPopularMoviesResponse()
        val mockApiKey = "4e017aafa0c4da4d663bc40fa6d6afe0"

        whenever(apiService.getMoviePopular(mockApiKey)).thenReturn(
            ApiResponse.of {
                Response.success(mockPopularResponse)
            }
        )

        repository.getPopularMovies(
            onStart = {},
            onComplete = {},
            onError = {},
            mockApiKey
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val mockMovie = mockPopularResponse.results[0]
            val expectedItem = awaitItem()[0]

            assertEquals(expectedItem.id, mockMovie.id)
            assertEquals(expectedItem.title, mockMovie.title)
            assertEquals(expectedItem.posterPath, mockMovie.posterPath)
            awaitComplete()
        }

        val mockMovies = mockPopularMoviesResponse().results.map {
            it.toEntity()
        }
        verify(movieDao, times(1)).insertAllMovies(mockMovies)
        verify(apiService, times(1)).getMoviePopular(mockApiKey)
        verifyNoMoreInteractions(apiService)
    }

}