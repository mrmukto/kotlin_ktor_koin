package com.test.kotlinktor.data.remote

import com.test.kotlinktor.data.model.Post

interface ApiService {
    suspend fun getPosts(): List<Post>
    suspend fun getPost(id: Int): Post
}