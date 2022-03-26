package com.sarfa.listdetailsexample.data.remote.base

import com.google.gson.JsonParseException
import com.sarfa.domain.model.response.base.ErrorType
import com.sarfa.domain.model.response.base.ResponseWrapper
import com.sarfa.listdetailsexample.data.remote.util.BaseNetworkHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SafeApiCall @Inject constructor(private val networkHelper: BaseNetworkHelper) {
    suspend operator fun <RESPONSE> invoke(
        coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
        apiCall: suspend () -> Response<RESPONSE>
    ): ResponseWrapper<RESPONSE> {
        return withContext(coroutineDispatcher) {
            var retryCount = -1
            if (!networkHelper.hasInternetConnection()) {
                ResponseWrapper.Error(ErrorType.NoInternetError)
            } else {
                var result: ResponseWrapper<RESPONSE>
                do {
                    retryCount++
                    result = callApi(apiCall)
                } while (result is ResponseWrapper.Error && (result.errorType is ErrorType.NetworkError || ((result.errorType as? ErrorType.ApiError)?.statusCode == 401)) && retryCount < 1)
                result
            }
        }
    }

    private suspend fun <RESPONSE> callApi(
        apiCall: suspend () -> Response<RESPONSE>,
    ): ResponseWrapper<RESPONSE> {
        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    ResponseWrapper.Success(it)
                } ?: ResponseWrapper.Error(ErrorType.UnknownError(null))
            } else {
                ResponseWrapper.Error(
                    ErrorType.ApiError(
                        response.code(),
                        response.errorBody()?.string()
                    )
                )
            }

        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    ResponseWrapper.Error(ErrorType.NetworkError(throwable))
                }
                is JsonParseException -> {
                    ResponseWrapper.Error(ErrorType.JsonParseError(throwable))
                }
                else -> {
                    ResponseWrapper.Error(ErrorType.UnknownError(throwable))
                }
            }
        }
    }
}
