package com.husqvarna.popularmovies.api.models

/**
 * Wrapper class for handling a API results.
 */
sealed interface ApiResult<out T> {
    /** A generic success data class return when api result is successful */
    data class Success<out T>(val data: T) : ApiResult<T>

    /** A failure data class return when api result is failed */
    data class Error(val errorMessage: String) : ApiResult<Nothing>
}
