package com.bin.movie.ui.main

import androidx.lifecycle.ViewModel
import com.bin.movie.repository.MainRepository
import kotlinx.coroutines.flow.collect

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {


    suspend fun fetchPopularMovies() {
        mainRepository.getPopularMovies(
            onStart = {},
            onComplete = {},
            onError = {},
            "4e017aafa0c4da4d663bc40fa6d6afe0"
        ).collect {
            println("pesan: Dapet nih")
            println(it.results)
        }
    }


}
