package com.husqvarna.popularmovies.api.base

import com.husqvarna.popularmovies.api.models.ApiResult
import retrofit2.Response

const val ERROR_RESPONSE_NULL = "Response body is null"
const val ERROR_UNKNOWN = "Something went wrong"

/**
 * A helper class to execute retrofit calls safely and wrap it its result in [ApiResult].
 */
open class SafeApi {
    /**
     * Executes a retrofit call and wraps its result in [ApiResult].
     * @param call The retrofit call to execute.
     * @return [ApiResult] with the result of the call.
     */
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
