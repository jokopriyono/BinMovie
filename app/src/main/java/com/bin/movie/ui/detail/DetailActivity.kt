package com.bin.movie.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bin.movie.R
import com.bin.movie.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}