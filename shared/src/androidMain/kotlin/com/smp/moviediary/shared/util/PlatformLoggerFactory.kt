package com.smp.moviediary.shared.util

import android.util.Log
import io.ktor.client.features.logging.*

actual fun createPlatformLogger(defaultTag: String): PlatformLogger {
    return AndroidPlatformLogger(defaultTag)
}

actual fun createPlatformKtorLogger(defaultTag: String): Logger {
    return AndroidPlatformKtorLogger(defaultTag)
}

class AndroidPlatformLogger(defaultTag: String) : PlatformLogger(defaultTag) {
    override fun v(message: String) {
        Log.v(defaultTag, message)
    }

    override fun v(tag: String, message: String) {
        Log.v(tag, message)
    }

    override fun i(message: String) {
        Log.i(defaultTag, message)
    }

    override fun i(tag: String, message: String) {
        Log.i(tag, message)
    }

    override fun d(message: String) {
        Log.d(defaultTag, message)
    }

    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun w(message: String) {
        Log.w(defaultTag, message)
    }

    override fun w(tag: String, message: String) {
        Log.w(tag, message)
    }

    override fun e(message: String) {
        Log.e(defaultTag, message)
    }

    override fun e(tag: String, message: String) {
        Log.e(tag, message)
    }
}

class AndroidPlatformKtorLogger(defaultTag: String) : Logger {
    private val platformLogger = AndroidPlatformLogger(defaultTag)

    override fun log(message: String) {
        platformLogger.d(message)
    }
}