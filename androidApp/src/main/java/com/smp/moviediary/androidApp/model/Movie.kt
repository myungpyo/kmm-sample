package com.smp.moviediary.androidApp.model

import android.text.Spanned

data class Movie(
    val id: Long,
    val title: String,
    val spannedTitle: Spanned,
    val subTitle: String?,
    val spannedSubTitle: Spanned?,
    val thumb: String?,
    val director: String?,
    val actor: String?,
    val publishedAt: String?,
    val rating: Float,
    val scrap: Boolean,
    val memo: String?
)