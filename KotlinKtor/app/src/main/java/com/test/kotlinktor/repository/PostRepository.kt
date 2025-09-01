package com.test.kotlinktor.repository

import com.test.kotlinktor.data.model.Post
import com.test.kotlinktor.data.remote.ApiService
import com.test.kotlinktor.utils.Result

class PostRepository(private val api: ApiService) {
    suspend fun fetchPosts(): Result<List<Post>> =
        try { Result.Success(api.getPosts()) }
        catch (t: Throwable) {Result.Error(t.message ?: "Unknown error", t) }


    suspend fun fetchPost(id: Int): Result<Post> =
        try { Result.Success(api.getPost(id)) }
        catch (t: Throwable) { Result.Error(t.message ?: "Unknown error", t) }
}