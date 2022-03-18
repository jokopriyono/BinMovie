package com.bin.movie.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bin.movie.R
import com.bin.movie.base.BaseActivity
import com.bin.movie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_activity_example)
        binding.navView.setupWithNavController(navController)

        lifecycleScope.launch {
            viewModel.fetchPopularMovies()
            viewModel.fetchUpComingMovies()
        }
    }

    override fun setupObserver() {
        viewModel.message.observe(this) {
            showMessageToast(it)
        }
        viewModel.loading.observe(this) {
            if (it) showLoading() else hideLoading()
        }
    }
}