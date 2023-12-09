package com.bosta_task.domain.common

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val exception: Throwable) : Resource<Nothing>
    object Loading : Resource<Nothing>
}

fun <T> Flow<T>.toResource(): Flow<Resource<T>> {
    return this
        .map<T, Resource<T>> {
            Resource.Success(it)
        }
        .onStart { emit(Resource.Loading) }
        .catch {
            when(it){
                is CancellationException-> throw it
                else->emit(Resource.Error(exception = it))
            }
        }
}