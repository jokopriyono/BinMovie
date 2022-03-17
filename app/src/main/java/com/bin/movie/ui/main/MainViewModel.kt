package com.bin.movie.ui.main

import com.bin.movie.base.BaseViewModel
import com.bin.movie.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel() {

    suspend fun fetchPopularMovies() {
        mainRepository.getPopularMovies(
            onStart = {
                showLoading()
            },
            onComplete = {
                hideLoading()
            },
            onError = {
                _message.value = it
            },
            "4e017aafa0c4da4d663bc40fa6d6afe0"
        ).collect {
            println("pesan: Dapet nih")
            println(it.results)
        }
    }


}
