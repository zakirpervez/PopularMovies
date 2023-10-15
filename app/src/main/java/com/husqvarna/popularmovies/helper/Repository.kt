package com.husqvarna.popularmovies.helper

import com.husqvarna.popularmovies.api.ApiDataSource
import com.husqvarna.popularmovies.api.ApiException
import javax.inject.Inject

class Repository @Inject constructor(private val apiDataSource: ApiDataSource) {
    @Throws(ApiException::class)
    suspend fun fetchPopularMovies(page: Int) = apiDataSource.fetchPopularMovies(page)

    @Throws(ApiException::class)
    suspend fun fetchMovieDetails(movieId: Int) = apiDataSource.fetchMovieDetails(movieId)
}
