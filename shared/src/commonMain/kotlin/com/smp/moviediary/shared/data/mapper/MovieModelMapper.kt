package com.smp.moviediary.shared.data.mapper

import com.smp.moviediary.shared.data.model.MovieModel
import com.smp.moviediary.shared.domain.entity.MovieEntity
import com.smp.moviediary.shared.domain.mapper.Mapper


object MovieModelMapper : Mapper<MovieModel, MovieEntity> {

    override fun map(data: MovieModel): MovieEntity {
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

    override fun reverse(data: MovieEntity): MovieModel {
        return MovieModel(
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