package com.smp.moviediary.shared.data.network

import com.smp.moviediary.shared.data.model.MovieApiResponseModel
import com.smp.moviediary.shared.util.createPlatformKtorLogger
import com.smp.shared.BuildConfig
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json


class MovieApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
        install(Logging) {
            logger = createPlatformKtorLogger("Network")
            level = LogLevel.NONE
        }
        defaultRequest {
            header("X-Naver-Client-Id", BuildConfig.CLIENT_ID)
            header("X-Naver-Client-Secret", BuildConfig.CLIENT_SECRET)
        }
    }

    suspend fun searchMovies(keyword: String): MovieApiResponseModel {
        return httpClient.get(NEWS_ENDPOINT) {
            parameter("query", keyword)
            parameter("start", DEFAULT_PAGE_NUM)
            parameter("display", DEFAULT_PAGE_SIZE)
        }
    }

    companion object {
        private const val DEFAULT_PAGE_NUM = 1
        private const val DEFAULT_PAGE_SIZE = 50
        private const val NEWS_ENDPOINT = "https://openapi.naver.com/v1/search/movie.json"
    }
}