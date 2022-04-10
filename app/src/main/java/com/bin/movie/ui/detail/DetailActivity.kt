package com.bin.movie.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bin.movie.databinding.ActivityDetailBinding
import com.bin.movie.ui.main.MainActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var isActivityFromDeepLink = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appLinkAction: String? = intent.action
        val appLinkData: Uri? = intent.data

        if (appLinkAction == Intent.ACTION_VIEW && appLinkData != null) {
            loadDeepLink(appLinkAction, appLinkData)
        }

        setupRecycler()
    }

    private fun loadDeepLink(appLinkAction: String, appLinkData: Uri) {
        // contoh link -> https://www.bin.movie/detail?name=Harry%20Potter%20Jakarta
        isActivityFromDeepLink = true
        appLinkData.getQueryParameter("name")?.let { movieName ->
            binding.txtTitle.text = movieName
        }
    }

    private fun setupRecycler() {
        val mGenre = listOf("Adventure", "Action", "Comedy")

        binding.recyclerGenre.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            adapter = GenreAdapter(mGenre)
        }

        binding.recyclerCast.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            adapter = CastAdapter()
        }
    }

    override fun onDestroy() {
        // hapus ini kalau memang tidak diperlukan ke home
        if (isActivityFromDeepLink) {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        super.onDestroy()
    }
}