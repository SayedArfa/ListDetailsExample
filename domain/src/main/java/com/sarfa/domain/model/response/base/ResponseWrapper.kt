package com.sarfa.domain.model.response.base

import java.io.IOException

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val data: T) : ResponseWrapper<T>()
    data class Error(val errorType: ErrorType) : ResponseWrapper<Nothing>()
    object Loading : ResponseWrapper<Nothing>()
}

sealed class ErrorType {
    data class ApiError(val statusCode: Int, val errorBody: String?) : ErrorType()
    object NoInternetError : ErrorType()
    data class NetworkError(val exception: IOException) : ErrorType()
    data class JsonParseError(val exception: Throwable?) : ErrorType()
    data class UnknownError(val exception: Throwable?) : ErrorType()
}