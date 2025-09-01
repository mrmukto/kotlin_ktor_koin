package com.test.kotlinktor.utils

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception1: String, val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
}