package com.san.core.utils


sealed class Resource<out T>() {
    data class Success<out T>(val result: T) : Resource<T>()
    data class Failed<out T>(val result: T) : Resource<T>()
    data class Error(val exception: Throwable) : Resource<Nothing>()
    data class Loader(val isLoading: Boolean) : Resource<Nothing>()
    object NoNetwork : Resource<Nothing>()
}

sealed class BoundResource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : BoundResource<T>(data)
    class Loading<T>(data: T? = null) : BoundResource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : BoundResource<T>(data, throwable)
}
