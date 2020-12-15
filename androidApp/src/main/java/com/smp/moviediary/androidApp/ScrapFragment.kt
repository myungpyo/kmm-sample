package com.smp.moviediary.androidApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.smp.moviediary.androidApp.databinding.FragmentScrapBinding
import com.smp.moviediary.androidApp.extension.observeNotNull
import com.smp.moviediary.androidApp.extension.visible
import com.smp.moviediary.androidApp.model.Movie
import com.smp.moviediary.androidApp.util.ViewData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScrapFragment : Fragment(), SearchListAdapter.Listener {

    companion object {
        val TAG = "${ScrapFragment::class.qualifiedName}"
    }

    private val viewModel: MovieViewModel by viewModels()
    private var _binding: FragmentScrapBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScrapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scrapContainerView.setOnRefreshListener {
            reloadScrapMovies()
        }

        with(binding.scrapListView) {
            layoutManager = LinearLayoutManager(context)
            adapter = SearchListAdapter(this@ScrapFragment)
        }

        observeNotNull(viewModel.movieData) { searchData ->
            bindSearchData(searchData)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        reloadScrapMovies()
    }

    private fun bindSearchData(searchData: ViewData<List<Movie>>) {
        when (searchData) {
            ViewData.Loading -> {
                binding.scrapContainerView.isRefreshing = true
            }
            is ViewData.Fail -> {
                binding.scrapContainerView.isRefreshing = false
            }
            is ViewData.Success -> {
                binding.scrapContainerView.isRefreshing = false
                (binding.scrapListView.adapter as? SearchListAdapter)?.let { adapter ->
                    adapter.swapData(searchData.value)
                    binding.scrapEmptyView.visible = adapter.itemCount == 0
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun reloadScrapMovies() {
        viewModel.loadScrapMovies()
    }

    override fun onUpdateScrap(id: Long, scrap: Boolean) {
        viewModel.updateScrap(id, scrap)
    }

}