package com.smp.moviediary.shared.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual fun dispatcher(): CoroutineDispatcher = Dispatchers.Main