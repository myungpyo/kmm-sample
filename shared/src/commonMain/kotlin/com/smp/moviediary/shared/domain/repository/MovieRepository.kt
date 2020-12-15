package com.smp.moviediary.shared.domain.repository

import com.smp.moviediary.shared.domain.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun searchMovies(keyword: String): Flow<List<MovieEntity>>
    fun loadScrapMovies(): Flow<List<MovieEntity>>
    suspend fun setScrap(id: Long, scrap: Boolean)
    suspend fun clearMovies()
}