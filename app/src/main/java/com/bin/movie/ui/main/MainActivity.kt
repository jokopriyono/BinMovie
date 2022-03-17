package com.bin.movie.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bin.movie.MyViewModelFactory
import com.bin.movie.R
import com.bin.movie.data.local.BinDatabase
import com.bin.movie.data.remote.ApiClient
import com.bin.movie.databinding.ActivityMainBinding
import com.bin.movie.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = ApiClient.instance
        val database = BinDatabase.instance(this)
        val mainRepository = MainRepository(
            apiService,
            database.movieDao(),
            Dispatchers.IO
        )
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        )[MainViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_example)
        binding.navView.setupWithNavController(navController)

        lifecycleScope.launch {
            viewModel.fetchPopularMovies()
        }
    }
}