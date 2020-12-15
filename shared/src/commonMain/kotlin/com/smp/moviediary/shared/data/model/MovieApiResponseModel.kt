package com.smp.moviediary.shared.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieApiResponseModel(
    @SerialName("total")
    val total: Int,
    @SerialName("start")
    val start: Int,
    @SerialName("display")
    val display: Int,
    @SerialName("items")
    val items: List<MovieModel>
)