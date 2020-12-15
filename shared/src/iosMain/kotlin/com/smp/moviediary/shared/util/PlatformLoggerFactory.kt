package com.smp.moviediary.shared.util

import io.ktor.client.features.logging.*
import platform.Foundation.NSLog

actual fun createPlatformLogger(defaultTag: String): PlatformLogger {
    return IOSPlatformLogger(defaultTag)
}

actual fun createPlatformKtorLogger(defaultTag: String): Logger {
    return IOSPlatformKtorLogger(defaultTag)
}

class IOSPlatformLogger(defaultTag: String) : PlatformLogger(defaultTag) {

    private fun makeLogMessage(severity: String, tag: String, message: String): String {
        return "($severity:$tag) $message"
    }

    override fun v(message: String) {
        NSLog(makeLogMessage("V", defaultTag, message))
    }

    override fun v(tag: String, message: String) {
        NSLog(makeLogMessage("V", tag, message))
    }

    override fun i(message: String) {
        NSLog(makeLogMessage("I", defaultTag, message))
    }

    override fun i(tag: String, message: String) {
        NSLog(makeLogMessage("I", tag, message))
    }

    override fun d(message: String) {
        NSLog(makeLogMessage("D", defaultTag, message))
    }

    override fun d(tag: String, message: String) {
        NSLog(makeLogMessage("D", tag, message))
    }

    override fun w(message: String) {
        NSLog(makeLogMessage("W", defaultTag, message))
    }

    override fun w(tag: String, message: String) {
        NSLog(makeLogMessage("W", tag, message))
    }

    override fun e(message: String) {
        NSLog(makeLogMessage("E", defaultTag, message))
    }

    override fun e(tag: String, message: String) {
        NSLog(makeLogMessage("E", tag, message))
    }
}

class IOSPlatformKtorLogger(defaultTag: String) : Logger {
    private val platformLogger = IOSPlatformLogger(defaultTag)

    override fun log(message: String) {
        platformLogger.d(message)
    }
}