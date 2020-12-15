package com.smp.moviediary.shared.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieModel(
    @SerialName("id")
    val id: Long = -1L,
    @SerialName("title")
    val title: String,
    @SerialName("subtitle")
    val subTitle: String? = null,
    @SerialName("image")
    val thumb: String? = null,
    @SerialName("director")
    val director: String? = null,
    @SerialName("actor")
    val actor: String? = null,
    @SerialName("pubDate")
    val publishedAt: String? = null,
    @SerialName("userRating")
    val rating: Float = 0f,
    @SerialName("scrap")
    val scrap: Boolean = false,
    @SerialName("memo")
    val memo: String? = null
)