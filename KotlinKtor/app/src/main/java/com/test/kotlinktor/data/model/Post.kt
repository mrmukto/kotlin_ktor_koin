package com.test.kotlinktor.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Post(
    val id: Int,
    val title: String,
    val body: String,
    @SerialName("userId") val userId: Int
)
