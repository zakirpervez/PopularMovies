package com.husqvarna.popularmovies.api.source

import com.husqvarna.popularmovies.api.ApiService
import com.husqvarna.popularmovies.api.base.SafeApi
import javax.inject.Inject

class ApiDataSource @Inject constructor(private val apiService: ApiService) : SafeApi {
    suspend fun fetchPopularMovies(page: Int) = execute {
        apiService.fetchPopularMovies(page)
    }

    suspend fun fetchMovieDetails(movieId: Int) = execute {
        apiService.fetchMovieDetails(movieId)
    }
}
