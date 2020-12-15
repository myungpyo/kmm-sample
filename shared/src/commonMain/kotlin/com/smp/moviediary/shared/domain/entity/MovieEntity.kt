package com.smp.moviediary.shared.domain.entity

data class MovieEntity(
    val id: Long,
    val title: String,
    val subTitle: String?,
    val thumb: String?,
    val director: String?,
    val actor: String?,
    val publishedAt: String?,
    val rating: Float,
    val scrap: Boolean,
    val memo: String?
) 