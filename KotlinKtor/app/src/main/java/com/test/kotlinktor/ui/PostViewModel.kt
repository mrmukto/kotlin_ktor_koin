package com.test.kotlinktor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.kotlinktor.data.model.Post
import com.test.kotlinktor.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.test.kotlinktor.utils.Result

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    private val _posts = MutableStateFlow<Result<List<Post>>>(Result.Loading)
    val posts: StateFlow<Result<List<Post>>> = _posts

    fun loadPosts() {
        viewModelScope.launch {
            _posts.value = Result.Loading
            _posts.value = repository.fetchPosts()
        }
    }
}