package com.smp.moviediary.shared.di

import com.smp.moviediary.shared.data.MovieDataSource
import com.smp.moviediary.shared.data.MovieRepositoryImpl
import com.smp.moviediary.shared.data.network.MovieApi
import com.smp.moviediary.shared.data.network.MovieRemoteDataSource
import com.smp.moviediary.shared.data.storage.Database
import com.smp.moviediary.shared.data.storage.MovieLocalDataSource
import com.smp.moviediary.shared.domain.repository.MovieRepository
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.koinApplication
import org.koin.dsl.module

val movieModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            get(qualifier = named("local")),
            get(qualifier = named("remote"))
        )
    }
    factory<MovieDataSource>(named("local")) { MovieLocalDataSource(get()) }
    factory<MovieDataSource>(named("remote")) { MovieRemoteDataSource(get()) }
    factory { Database(get()) }
    factory { MovieApi() }

}

expect val platformMovieModule: Module

fun createKoinApp() = koinApplication {
    modules(movieModule, platformMovieModule)
}