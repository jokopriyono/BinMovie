package com.bin.movie.ui.search

import androidx.lifecycle.MutableLiveData
import com.bin.movie.base.BaseViewModel
import com.bin.movie.data.model.local.MovieEntity
import com.bin.movie.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {

    val searchResult = MutableLiveData<List<MovieEntity>>()

    suspend fun searchMovie(query: String) {
        mainRepository.searchMovies(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.postValue(it)
            },
            "4e017aafa0c4da4d663bc40fa6d6afe0",
            query
        ).collect {
            searchResult.postValue(it)
        }
    }

}
