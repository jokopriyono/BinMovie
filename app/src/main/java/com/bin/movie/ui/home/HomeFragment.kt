package com.bin.movie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bin.movie.databinding.FragmentHomeBinding
import com.bin.movie.ui.main.MainViewModel

class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val popularAdapter = PopularAdapter(mutableListOf()) {
        // TODO intent to detail movie screen
    }
    private val upComingAdapter = UpcomingAdapter(mutableListOf()) {
        // TODO intent to detail movie screen
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerPopularMovies.apply {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = popularAdapter
        }
        binding.recyclerComingSoon.apply {
            layoutManager = LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = upComingAdapter
        }

        setupObserver()
    }

    private fun setupObserver() {
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            popularAdapter.populars.addAll(it)
            popularAdapter.notifyDataSetChanged()
        }
        viewModel.upComingMovies.observe(viewLifecycleOwner) {
            upComingAdapter.upComingMovies.addAll(it)
            upComingAdapter.notifyDataSetChanged()
        }
    }

}