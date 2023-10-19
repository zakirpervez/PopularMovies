package com.husqvarna.popularmovies.api

import com.husqvarna.popularmovies.api.source.ApiDataSource
import javax.inject.Inject

/**
 * Mediator class to connect the data sources with view-models.
 */
class Repository @Inject constructor(private val apiDataSource: ApiDataSource) {

    /**
     * Fetch the popular movies from the [ApiDataSource].
     * @param page The page number to fetch the data.
     * @see [ApiDataSource.fetchPopularMovies]
     * @return [ApiResult] of [PopularMoviesResponse] object.
     */
    suspend fun fetchPopularMovies(page: Int) = apiDataSource.fetchPopularMovies(page)

    /**
     * Fetch the movie details from the [ApiDataSource].
     * @param movieId The movie id to fetch the data.
     * @see [ApiDataSource.fetchMovieDetails]
     * @return [ApiResult] of [MovieDetailsResponse] object.
     */
    suspend fun fetchMovieDetails(movieId: Int) = apiDataSource.fetchMovieDetails(movieId)
}
