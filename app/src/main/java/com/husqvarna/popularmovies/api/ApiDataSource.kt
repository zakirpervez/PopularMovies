package com.husqvarna.popularmovies.api

import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: ApiService) : SafeApi {
    @Throws(ApiException::class)
    suspend fun fetchPopularMovies(page: Int) = execute {
        apiService.fetchPopularMovies(page)
    }

    @Throws(ApiException::class)
    suspend fun fetchMovieDetails(movieId: Int) = execute {
        apiService.fetchMovieDetails(movieId)
    }
}
