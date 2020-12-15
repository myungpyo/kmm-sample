package com.smp.moviediary.shared.data

import com.smp.moviediary.shared.data.mapper.MovieModelMapper
import com.smp.moviediary.shared.data.model.MovieModel
import com.smp.moviediary.shared.domain.entity.MovieEntity
import com.smp.moviediary.shared.domain.repository.MovieRepository
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl(
    private val localMovieDataSource: MovieDataSource,
    private val remoteMovieDataSource: MovieDataSource
) : MovieRepository {

    override fun searchMovies(keyword: String): Flow<List<MovieEntity>> {
        return remoteMovieDataSource.searchMovies(keyword)
            .flatMapConcat { remoteMovies ->
                bindLocalProperties(remoteMovies)
            }
            .flatMapConcat { remoteMovies ->
                localMovieDataSource.addOrUpdateMovies(remoteMovies)
                localMovieDataSource.searchMovies(keyword)
            }.map { searchedMovies -> searchedMovies.map(MovieModelMapper::map) }
    }

    override fun loadScrapMovies(): Flow<List<MovieEntity>> {
        return localMovieDataSource.loadScrapMovies()
            .map { scrapMovies -> scrapMovies.map(MovieModelMapper::map) }
    }

    override suspend fun setScrap(id: Long, scrap: Boolean) {
        localMovieDataSource.setScrap(id, scrap)
    }

    override suspend fun clearMovies() {
        localMovieDataSource.clearMovies()
    }

    private suspend fun bindLocalProperties(remoteMovies: List<MovieModel>): Flow<List<MovieModel>> {
        val localMovieMap =
            localMovieDataSource.loadMovies(remoteMovies.map { it.id }).associateBy { it.id }
        val resultMovies = remoteMovies.map { remoteMovie ->
            val localMovie = localMovieMap[remoteMovie.id] ?: return@map remoteMovie
            return@map remoteMovie.copy(scrap = localMovie.scrap, memo = localMovie.memo)
        }
        return flowOf(resultMovies)
    }
}