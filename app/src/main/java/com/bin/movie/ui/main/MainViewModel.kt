package com.bin.movie.ui.main

import androidx.lifecycle.MutableLiveData
import com.bin.movie.base.BaseViewModel
import com.bin.movie.data.model.local.MovieEntity
import com.bin.movie.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {

    val movies = MutableLiveData<List<MovieEntity>>()

    suspend fun fetchPopularMovies() {
        mainRepository.getPopularMovies(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.postValue(it)
            },
            "4e017aafa0c4da4d663bc40fa6d6afe0"
        ).collect {
            movies.postValue(it)
        }
    }

    suspend fun fetchUpComingMovies() {
        mainRepository.getUpComingMovies(
            onStart = {

            },
            onComplete = {

            },
            onError = {
                _message.postValue(it)
            },
            "4e017aafa0c4da4d663bc40fa6d6afe0"
        ).collect {
            movies.postValue(it)
        }
    }


}
