package com.smp.moviediary.androidApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.smp.moviediary.androidApp.databinding.FragmentSearchBinding
import com.smp.moviediary.androidApp.extension.observeNotNull
import com.smp.moviediary.androidApp.extension.visible
import com.smp.moviediary.androidApp.model.Movie
import com.smp.moviediary.androidApp.util.ViewData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchListAdapter.Listener {

    companion object {
        val TAG = "${SearchFragment::class.qualifiedName}"
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.keywordSubmitButton.setOnClickListener {
            requestSearch()
        }

        binding.resultContainerView.setOnRefreshListener {
            requestSearch()
        }

        with(binding.resultListView) {
            layoutManager = LinearLayoutManager(context)
            adapter = SearchListAdapter(this@SearchFragment)
        }

        observeNotNull(viewModel.movieData) { searchData ->
            bindSearchData(searchData)
        }
    }

    private fun bindSearchData(searchData: ViewData<List<Movie>>) {
        when (searchData) {
            ViewData.Loading -> {
                binding.resultContainerView.isRefreshing = true
            }
            is ViewData.Fail -> {
                binding.resultContainerView.isRefreshing = false
            }
            is ViewData.Success -> {
                binding.resultContainerView.isRefreshing = false
                (binding.resultListView.adapter as? SearchListAdapter)?.let { adapter ->
                    adapter.swapData(searchData.value)
                    binding.resultEmptyView.visible = adapter.itemCount == 0
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun requestSearch() {
        val keyword = binding.keywordInputView.text.toString()
        viewModel.searchMovies(keyword.trim())
    }

    override fun onUpdateScrap(id: Long, scrap: Boolean) {
        viewModel.updateScrap(id, scrap)
    }
}