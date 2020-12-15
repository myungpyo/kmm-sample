package com.smp.moviediary.shared.di

import com.smp.moviediary.shared.data.storage.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformMovieModule: Module
    get() = module {
        factory { DatabaseDriverFactory() }
    }