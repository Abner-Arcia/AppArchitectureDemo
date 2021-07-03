package com.example.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

inline fun <ResultType, RequestType> insertResource(
    crossinline cache: suspend () -> ResultType,
    crossinline remote: suspend () -> RequestType
) = flow<Resource<ResultType>> {
    emit(Resource.loading(null))
    try {
        remote()
        cache()
        emit(Resource.success(null))
    } catch(throwable: Throwable) {
        emit(Resource.error("Valió chorizo", null))
    }
}.flowOn(Dispatchers.IO)