package com.smp.moviediary.androidApp.di

import android.content.Context
import com.smp.moviediary.shared.MovieSDKForAndroid
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieSdkModule {

    @Provides
    @Singleton
    fun provideMovieSdk(@ApplicationContext context: Context): MovieSDKForAndroid {
        return MovieSDKForAndroid((context))
    }
}