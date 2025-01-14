package com.company.movies.util

import com.company.movies.domain.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): Flow<DataState<T>> {
    return flow {
        emit(DataState.Loading)
        try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(DataState.Success(body))
            } else {
                val errorBody = response.errorBody()?.string()
                val message = parseErrorBody(errorBody) ?: response.message()
                emit(DataState.Error(response.code(), message))
            }
        } catch (e: HttpException) {
            emit(DataState.Error(e.code(), e.message()))
        } catch (e: Throwable) {
            emit(DataState.Error(0, e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)
}