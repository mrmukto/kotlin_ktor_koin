package com.test.kotlinktor.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.io.File

object KtorClientProvider {
    fun create(cacheDir: File, isDebug: Boolean = true): HttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .cache(Cache(File(cacheDir, "http_cache"), 10L * 1024 * 1024)) // 10 MB cache
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer token_here")
                    .build()
                chain.proceed(request)
            })
            .build()

        return HttpClient(OkHttp) {
            engine { preconfigured = okHttpClient }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    explicitNulls = false
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 30_000
                connectTimeoutMillis = 15_000
                socketTimeoutMillis = 30_000
            }


            install(Logging) {
                logger = Logger.DEFAULT
                level = if (isDebug) LogLevel.BODY else LogLevel.NONE
            }
        }
    }
}