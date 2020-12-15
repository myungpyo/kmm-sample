package com.smp.moviediary.shared.data.storage

import com.smp.moviediary.shared.data.model.MovieModel
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDB(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDBQueries

    internal fun searchMovies(keyword: String): Flow<List<MovieModel>> {
        return dbQuery.searchMovies(
            keyword = keyword,
            mapper = ::mapMovie
        ).asFlow().mapToList()
    }

    internal fun insertOrUpdateMovies(movies: List<MovieModel>) {
        dbQuery.transaction {
            movies.forEach { movie ->
                dbQuery.upsertMovie(
                    id = movie.id,
                    title = movie.title,
                    subTitle = movie.subTitle,
                    thumb = movie.thumb,
                    director = movie.director,
                    actor = movie.actor,
                    publishedAt = movie.publishedAt,
                    rating = movie.rating,
                    scrap = movie.scrap,
                    memo = movie.memo
                )
            }
        }
    }

    internal fun selectMovies(ids: List<Long>): List<MovieModel> {
        return dbQuery.selectMovies(id = ids, mapper = ::mapMovie).executeAsList()
    }

    internal fun selectScrapMovies(): Flow<List<MovieModel>> {
        return dbQuery.selectScrapMovies(
            mapper = ::mapMovie
        ).asFlow().mapToList()
    }

    internal fun clearDatabase() {
        dbQuery.transaction {
            removeAllMovies()
        }
    }

    internal fun insertMovie(movie: MovieModel) {
        dbQuery.insertMovie(
            id = movie.id,
            title = movie.title,
            subTitle = movie.subTitle,
            thumb = movie.thumb,
            director = movie.director,
            actor = movie.actor,
            publishedAt = movie.publishedAt,
            rating = movie.rating,
            scrap = movie.scrap,
            memo = movie.memo
        )
    }

    internal fun updateMovie(movie: MovieModel) {
        dbQuery.updateMovie(
            id = movie.id,
            title = movie.title,
            subTitle = movie.subTitle,
            thumb = movie.thumb,
            director = movie.director,
            actor = movie.actor,
            publishedAt = movie.publishedAt,
            rating = movie.rating,
            scrap = movie.scrap,
            memo = movie.memo
        )
    }

    internal fun updateScrap(id: Long, scrap: Boolean) {
        dbQuery.updateScrap(
            id = id,
            scrap = scrap
        )
    }

    internal fun removeAllMovies() {
        dbQuery.removeAllMovies()
    }

    private fun mapMovie(
        id: Long,
        title: String,
        subTitle: String?,
        thumb: String?,
        director: String?,
        actor: String?,
        publishedAt: String?,
        rating: Float,
        scrap: Boolean,
        memo: String?
    ): MovieModel {
        return MovieModel(
            id = id,
            title = title,
            subTitle = subTitle,
            thumb = thumb,
            director = director,
            actor = actor,
            publishedAt = publishedAt,
            rating = rating,
            scrap = scrap,
            memo = memo
        )
    }
}