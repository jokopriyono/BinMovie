package com.bin.movie.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bin.movie.databinding.FragmentSearchBinding
import com.bin.movie.dialog.CustomLoadingDialog
import com.bin.movie.ui.detail.DetailActivity
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var loadingUI: CustomLoadingDialog
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingUI = CustomLoadingDialog(requireContext())
        binding.edtSearch.setOnEditorActionListener { textView, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                lifecycleScope.launch {
                    viewModel.searchMovie(textView.text.toString())
                }
            }
            true
        }
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.searchResult.observe(viewLifecycleOwner) {
            binding.recyclerPopular.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = SearchAdapter(it.toMutableList()) {
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) loadingUI.show()
            else loadingUI.hide()
        }
        viewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

}