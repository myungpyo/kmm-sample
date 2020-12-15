package com.smp.moviediary.androidApp.mapper

import androidx.core.text.HtmlCompat
import com.smp.moviediary.androidApp.model.Movie
import com.smp.moviediary.shared.domain.entity.MovieEntity
import com.smp.moviediary.shared.domain.mapper.Mapper


object MovieMapper : Mapper<MovieEntity, Movie> {

    override fun map(data: MovieEntity): Movie {
        return Movie(
            id = data.id,
            title = data.title,
            spannedTitle = HtmlCompat.fromHtml(data.title, HtmlCompat.FROM_HTML_MODE_LEGACY),
            subTitle = data.subTitle,
            spannedSubTitle = data.subTitle?.let { subTitle ->
                HtmlCompat.fromHtml(subTitle, HtmlCompat.FROM_HTML_MODE_LEGACY)
            },
            thumb = data.thumb,
            director = data.director,
            actor = data.actor,
            publishedAt = data.publishedAt,
            rating = data.rating,
            scrap = data.scrap,
            memo = data.memo
        )
    }

    override fun reverse(data: Movie): MovieEntity {
        return MovieEntity(
            id = data.id,
            title = data.title,
            subTitle = data.subTitle,
            thumb = data.thumb,
            director = data.director,
            actor = data.actor,
            publishedAt = data.publishedAt,
            rating = data.rating,
            scrap = data.scrap,
            memo = data.memo
        )
    }
}