package com.smp.moviediary.shared

import com.smp.moviediary.shared.di.createKoinApp
import com.smp.moviediary.shared.domain.entity.MovieEntity
import com.smp.moviediary.shared.domain.repository.MovieRepository
import com.smp.moviediary.shared.util.CFlow
import com.smp.moviediary.shared.util.createPlatformLogger
import com.smp.moviediary.shared.util.wrap
import kotlinx.coroutines.flow.onCompletion
import platform.Foundation.NSLog

class MovieSDKForIOS() {

    private val logger = createPlatformLogger("MovieSDK")

    private val koinApp = createKoinApp()
    private val movieRepository: MovieRepository = koinApp.koin.get()

    @Throws(Exception::class)
    fun searchMovies(keyword: String): CFlow<List<MovieEntity>> {
        return movieRepository.searchMovies(keyword = keyword)
            .onCompletion {
                NSLog("searchMoviesTemp::onCompletion")
            }.wrap()
    }

    @Throws(Exception::class)
    fun loadScrapMovies(): CFlow<List<MovieEntity>> {
        return movieRepository.loadScrapMovies().wrap()
    }

    @Throws(Exception::class)
    suspend fun setScrapMovie(id: Long, scrap: Boolean) {
        return movieRepository.setScrap(id, scrap)
    }
}