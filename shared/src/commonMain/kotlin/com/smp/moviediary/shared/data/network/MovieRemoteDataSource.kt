package com.smp.moviediary.shared.data.network

import com.smp.moviediary.shared.data.MovieDataSource
import com.smp.moviediary.shared.data.model.MovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRemoteDataSource(private val movieApi: MovieApi) : MovieDataSource {
    override fun searchMovies(keyword: String): Flow<List<MovieModel>> {
        return flow {
            val result = movieApi.searchMovies(keyword)
                .items.map { movieModel ->
                    movieModel.copy(
                        id = movieModel.title.hashCode().toLong()
                    )
                }
            emit(result)
        }
    }

    override suspend fun addOrUpdateMovies(movies: List<MovieModel>) {
        throw UnsupportedOperationException()
    }

    override fun loadScrapMovies(): Flow<List<MovieModel>> {
        return flow {
            throw UnsupportedOperationException()
        }
    }

    override suspend fun loadMovies(movieIds: List<Long>): List<MovieModel> {
        throw UnsupportedOperationException()
    }

    override suspend fun setScrap(id: Long, scrap: Boolean) {
        throw UnsupportedOperationException()
    }

    override suspend fun clearMovies() {
        throw UnsupportedOperationException()
    }

}