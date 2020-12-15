package com.smp.moviediary.shared

import android.content.Context
import com.smp.moviediary.shared.di.createKoinApp
import com.smp.moviediary.shared.domain.entity.MovieEntity
import com.smp.moviediary.shared.domain.repository.MovieRepository
import com.smp.moviediary.shared.util.createPlatformLogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.buffer
import org.koin.dsl.module

class MovieSDKForAndroid(context: Context) {

    private val logger = createPlatformLogger("MovieSDK")

    private val koinApp = createKoinApp().modules(module { single { context } })
    private val movieRepository: MovieRepository = koinApp.koin.get()

    fun searchMovies(keyword: String): Flow<List<MovieEntity>> {
        MutableStateFlow(1).buffer()
        return movieRepository.searchMovies(keyword = keyword)
    }

    fun loadScrapMovies(): Flow<List<MovieEntity>> {
        return movieRepository.loadScrapMovies()
    }

    suspend fun setScrapMovie(id: Long, scrap: Boolean) {
        return movieRepository.setScrap(id, scrap)
    }
}