package com.test.kotlinktor.data.remote

import com.test.kotlinktor.data.model.Post
import com.test.kotlinktor.utils.RetryUtils.retry
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiServiceImpl(
    private val client: HttpClient,
    private val baseUrl: String
) : ApiService {
    override suspend fun getPosts(): List<Post> = retry(times = 3, initialDelayMs = 500) {
        client.get("$baseUrl/posts").body()
    }


    override suspend fun getPost(id: Int): Post = retry(times = 3, initialDelayMs = 500) {
        client.get("$baseUrl/posts/$id").body()
    }
}