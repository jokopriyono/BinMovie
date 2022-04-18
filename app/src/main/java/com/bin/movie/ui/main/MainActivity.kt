package com.bin.movie.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bin.movie.R
import com.bin.movie.base.BaseActivity
import com.bin.movie.databinding.ActivityMainBinding
import com.bin.movie.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val intentWithResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // TODO ambil data dari intent -> it.data
            // TODO atau ambil Activity.RESULT_OK atau Activity.RESULT_CANCELED
            // it.data
            // it.resultCode == Activity.RESULT_OK
            // it.resultCode == Activity.RESULT_CANCELED
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Contoh intent modern
         */
        val intent = Intent(this, DetailActivity::class.java)
        intentWithResult.launch(intent)
        /**
         * ====
         */

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