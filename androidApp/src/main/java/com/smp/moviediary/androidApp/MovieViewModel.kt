package com.smp.moviediary.androidApp

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.smp.moviediary.androidApp.mapper.MovieMapper
import com.smp.moviediary.androidApp.model.Movie
import com.smp.moviediary.androidApp.util.ViewData
import com.smp.moviediary.shared.MovieSDKForAndroid
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieViewModel @ViewModelInject constructor(
    private val movieSdk: MovieSDKForAndroid,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _movieData = MutableLiveData<ViewData<List<Movie>>>()
    val movieData: LiveData<ViewData<List<Movie>>> = _movieData

    private var activeFlowJob: Job = SupervisorJob(viewModelScope.coroutineContext[Job])

    fun searchMovies(keyword: String) {
        cancelActiveFlowJob()
        viewModelScope.launch(activeFlowJob) {
            _movieData.value = ViewData.Loading
            runCatching {
                movieSdk.searchMovies(keyword)
                    .cancellable()
                    .collect { searchedMovies ->
                        _movieData.value = ViewData.Success(searchedMovies.map(MovieMapper::map))
                    }
            }.onFailure { throwable ->
                _movieData.value = ViewData.Fail(throwable)
            }
        }
    }

    fun loadScrapMovies() {
        cancelActiveFlowJob()
        viewModelScope.launch {
            _movieData.value = ViewData.Loading
            runCatching {
                movieSdk.loadScrapMovies()
                    .cancellable()
                    .collect { searchedMovies ->
                        _movieData.value = ViewData.Success(searchedMovies.map(MovieMapper::map))

                    }
            }.onFailure { throwable ->
                _movieData.value = ViewData.Fail(throwable)
            }
        }
    }

    fun updateScrap(id: Long, scrap: Boolean) = viewModelScope.launch {
        runCatching {
            updateScrapInMemory(id, scrap)
            movieSdk.setScrapMovie(id, scrap)
        }.onFailure {
            updateScrapInMemory(id, !scrap)
        }
    }

    private fun updateScrapInMemory(id: Long, scrap: Boolean) {
        val currentList = (_movieData.value as? ViewData.Success)?.value ?: return
        val updatedList = currentList.map { movie ->
            if (movie.id == id) {
                movie.copy(scrap = scrap)
            } else {
                movie
            }
        }
        _movieData.value = ViewData.Success(updatedList)
    }

    private fun cancelActiveFlowJob() {
        activeFlowJob.cancel()
        activeFlowJob = SupervisorJob(viewModelScope.coroutineContext[Job])
    }
}