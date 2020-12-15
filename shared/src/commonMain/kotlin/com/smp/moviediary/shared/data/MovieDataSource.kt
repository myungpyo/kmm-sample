package com.smp.moviediary.shared.data

import com.smp.moviediary.shared.data.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    fun searchMovies(keyword: String): Flow<List<MovieModel>>
    fun loadScrapMovies(): Flow<List<MovieModel>>
    suspend fun loadMovies(movieIds: List<Long>): List<MovieModel>
    suspend fun addOrUpdateMovies(movies: List<MovieModel>)
    suspend fun setScrap(id: Long, scrap: Boolean)
    suspend fun clearMovies()
}