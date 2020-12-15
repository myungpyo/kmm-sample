package com.smp.moviediary.shared.util

import io.ktor.client.features.logging.*

expect fun createPlatformLogger(defaultTag: String): PlatformLogger
expect fun createPlatformKtorLogger(defaultTag: String): Logger

abstract class PlatformLogger(protected val defaultTag: String) {
    abstract fun v(message: String)
    abstract fun v(tag: String, message: String)
    abstract fun i(message: String)
    abstract fun i(tag: String, message: String)
    abstract fun d(message: String)
    abstract fun d(tag: String, message: String)
    abstract fun w(message: String)
    abstract fun w(tag: String, message: String)
    abstract fun e(message: String)
    abstract fun e(tag: String, message: String)
}