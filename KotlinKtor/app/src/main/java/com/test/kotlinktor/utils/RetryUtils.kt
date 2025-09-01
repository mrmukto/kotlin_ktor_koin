package com.test.kotlinktor.utils

import kotlinx.coroutines.delay

object RetryUtils {
    suspend inline fun <T> retry(times: Int, initialDelayMs: Long, block: () -> T): T {
        var currentDelay = initialDelayMs
        repeat(times - 1) {
            try { return block() } catch (e: Exception) {
                delay(currentDelay)
                currentDelay *= 2
            }
        }
        return block()
    }
}