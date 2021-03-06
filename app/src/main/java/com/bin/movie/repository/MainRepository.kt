package com.bin.movie.repository

import com.bin.movie.data.local.MovieDao
import com.bin.movie.data.remote.ApiService
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun uploadFileAndGetResult(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        url: String,
        file: MultipartBody.Part,
        token: RequestBody,
    ) = flow {
        val response = apiService.uploadFile(url, file, token)
        response.suspendOnSuccess {
            emit(this.data)
        }.onError {
            Timber.e(this.message())
            onError(this.message())
        }.onException {
            Timber.e(this.message())
            onError(this.message())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    suspend fun getPopularMovies(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        apiKey: String
    ) = flow {
        val response = apiService.getMoviePopular(apiKey)
        response.suspendOnSuccess {
            val movies = this.data.results.map {
                it.toEntity()
            }
            movieDao.insertAllMovies(movies)
            emit(movies)
        }.onError {
            Timber.e(this.message())
            onError(this.message())
        }.onException {
            Timber.e(this.message())
            onError(this.message())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    suspend fun getUpComingMovies(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        apiKey: String
    ) = flow {
        val response = apiService.getMovieUpcoming(apiKey)
        response.suspendOnSuccess {
            val movies = this.data.results.map {
                it.toEntity()
            }
            movieDao.insertAllMovies(movies)
            emit(movies)
        }.onError {
            Timber.e(this.message())
            onError(this.message())
        }.onException {
            Timber.e(this.message())
            onError(this.message())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

    suspend fun searchMovies(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        apiKey: String,
        query: String
    ) = flow {
        val response = apiService.searchMovies(apiKey, query)
        response.suspendOnSuccess {
            val movies = this.data.results.map {
                it.toEntity()
            }
            movieDao.insertAllMovies(movies)
            emit(movies)
        }.onError {
            Timber.e(this.message())
            onError(this.message())
        }.onException {
            Timber.e(this.message())
            onError(this.message())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)

}