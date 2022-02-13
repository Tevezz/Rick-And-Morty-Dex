package com.mbe.domain.common.model

sealed class Response<out T : Any> {
    data class Success<T : Any>(val data: T) : Response<T>()
    data class Error(val exception: Exception) : Response<Nothing>()
}