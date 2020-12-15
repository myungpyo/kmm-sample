package com.smp.moviediary.androidApp.extension

import android.view.View

var View.visible: Boolean
    get() {
        return visibility == View.VISIBLE
    }
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }