package com.smp.moviediary.androidApp.util

sealed class ViewData<out T> {

    object Loading : ViewData<Nothing>()
    class Fail(val throwable: Throwable) : ViewData<Nothing>()
    class Success<T>(val value: T) : ViewData<T>()
}

fun <T> Result<T>.toUiData(): ViewData<T> {
    val data = getOrElse {
        return ViewData.Fail(it)
    }
    return ViewData.Success(data)
}