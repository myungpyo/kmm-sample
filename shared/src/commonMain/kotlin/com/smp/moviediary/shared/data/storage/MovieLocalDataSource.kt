package com.smp.moviediary.shared.data.storage

import com.smp.moviediary.shared.data.MovieDataSource
import com.smp.moviediary.shared.data.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

internal class MovieLocalDataSource(private val database: Database) : MovieDataSource {
    override fun searchMovies(keyword: String): Flow<List<MovieModel>> =
        database.searchMovies(keyword)
            .flowOn(Dispatchers.Default)


    override suspend fun addOrUpdateMovies(movies: List<MovieModel>) =
        withContext(Dispatchers.Default) {
            database.insertOrUpdateMovies(movies)
        }

    override fun loadScrapMovies(): Flow<List<MovieModel>> =
        database.selectScrapMovies()
            .flowOn(Dispatchers.Default)

    override suspend fun loadMovies(ids: List<Long>): List<MovieModel> =
        withContext(Dispatchers.Default) {
            database.selectMovies(ids)
        }
    

    override suspend fun setScrap(id: Long, scrap: Boolean) =
        withContext(Dispatchers.Default) {
            database.updateScrap(id, scrap)
        }


    override suspend fun clearMovies() =
        withContext(Dispatchers.Default) {
            database.removeAllMovies()
        }

}