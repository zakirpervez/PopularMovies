package com.husqvarna.popularmovies.api

import com.husqvarna.popularmovies.api.source.ApiDataSource
import javax.inject.Inject

class Repository @Inject constructor(private val apiDataSource: ApiDataSource) {
    suspend fun fetchPopularMovies(page: Int) = apiDataSource.fetchPopularMovies(page)

    suspend fun fetchMovieDetails(movieId: Int) = apiDataSource.fetchMovieDetails(movieId)
}
