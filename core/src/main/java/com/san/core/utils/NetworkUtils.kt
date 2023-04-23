package com.san.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

fun <ResultType, RequestType> networkBoundResource(
    query: () -> Flow<ResultType>,
    fetch: suspend () -> RequestType,
    saveFetchResult: suspend (RequestType) -> Unit,
    shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(BoundResource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { BoundResource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { BoundResource.Error(throwable, it) }
        }
    } else {
        query().map { BoundResource.Success(it) }
    }

    emitAll(flow)
}


fun <ResultType> networkOnlyResource(
    context: Context,
    fetch: suspend () -> ResultType
) = flow {
    if (!isNetworkConnected(context)) {
        emit(Resource.Error(Error("No Network")))
        return@flow
    }
    val flow = flow {
        try {
            emit(Resource.Success(fetch()))
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
        }
    }
        .onStart { emit(Resource.Loader(true)) }
        .onCompletion { emit(Resource.Loader(false)) }

    emitAll(flow)
}

fun <ResultType> libResource(
    context: Context,
    fetch: suspend () -> ResultType
) = flow {
    if (!isNetworkConnected(context)) {
        emit(Resource.Error(Error("No Network")))
        return@flow
    }
    val flow = flow {
        try {
            emit(Resource.Success(fetch()))
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable))
        }
    }
        .onStart { emit(Resource.Loader(true)) }
        .onCompletion { emit(Resource.Loader(false)) }
        .flowOn(Dispatchers.IO)

    emitAll(flow)
}

fun isNetworkConnected(context: Context): Boolean {
    var result = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
    }
    return result
}