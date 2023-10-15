package com.husqvarna.popularmovies.api

import retrofit2.Response

private const val ERROR_RESPONSE_NULL = "Response body is null"
private const val ERROR_UNKNOWN = "Something went wrong"
interface SafeApi {
    @Throws(Exception::class)
    suspend fun <T> execute(call: suspend () -> Response<T>): T {
        val apiResponse = call.invoke()
        return if (apiResponse.isSuccessful) {
            apiResponse.body() ?: throw ApiException("$ERROR_RESPONSE_NULL ${apiResponse.code()}")
        } else {
            var error = apiResponse.message()
            if (error.isEmpty()) {
                error = ERROR_UNKNOWN
            }

            throw ApiException("$error ${apiResponse.code()}")
        }
    }
}
