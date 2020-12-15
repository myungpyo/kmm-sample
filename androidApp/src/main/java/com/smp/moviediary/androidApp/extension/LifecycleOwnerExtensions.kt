package com.smp.moviediary.androidApp.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LifecycleOwner.observeNotNull(liveData: LiveData<T>, observer: (t: T) -> Unit) {
    liveData.observe(this, androidx.lifecycle.Observer { t ->
        if (t == null) return@Observer
        observer.invoke(t)
    })
}