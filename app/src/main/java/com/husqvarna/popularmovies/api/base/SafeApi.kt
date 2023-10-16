package com.husqvarna.popularmovies.api.base

import com.husqvarna.popularmovies.api.models.ApiResult
import retrofit2.Response

private const val ERROR_RESPONSE_NULL = "Response body is null"
private const val ERROR_UNKNOWN = "Something went wrong"

interface SafeApi {
    suspend fun <T> execute(call: suspend () -> Response<T>): ApiResult<T> {
        try {
            val apiResponse = call.invoke()
            return if (apiResponse.isSuccessful) {
                val body = apiResponse.body()
                if (body != null) {
                    ApiResult.Success(body)
                } else {
                    ApiResult.Error("$ERROR_RESPONSE_NULL ${apiResponse.code()}")
                }
            } else {
                val error = apiResponse.message().takeIf { it.isNotEmpty() } ?: ERROR_UNKNOWN
                ApiResult.Error("$error ${apiResponse.code()}")
            }
        } catch (e: Exception) {
            // Handle exceptions, e.g., network issues
            return ApiResult.Error(e.message ?: ERROR_UNKNOWN)
        }
    }
}
