package com.husqvarna.popularmovies.api.source

import com.husqvarna.popularmovies.api.ApiService
import com.husqvarna.popularmovies.api.base.SafeApi
import javax.inject.Inject
import com.husqvarna.popularmovies.api.models.ApiResult
import com.husqvarna.popularmovies.api.models.response.PopularMoviesResponse
import com.husqvarna.popularmovies.api.models.response.MovieDetailsResponse

/**
 * Remote/API data source for fetching data from API.
 */
class ApiDataSource @Inject constructor(private val apiService: ApiService) : SafeApi() {

    /**
     * Fetch the popular movies from the [ApiService].
     * @param page to fetch the data.
     * @see [ApiService.fetchPopularMovies]
     * @return [ApiResult] of [PopularMoviesResponse] object.
     */
    suspend fun fetchPopularMovies(page: Int) = execute {
        apiService.fetchPopularMovies(page)
    }

    /**
     * Fetch the movie details from the [ApiService].
     * @param movieId to fetch the data.
     * @see [ApiService.fetchMovieDetails]
     * @return [ApiResult] of [MovieDetailsResponse] object.
     */
    suspend fun fetchMovieDetails(movieId: Int) = execute {
        apiService.fetchMovieDetails(movieId)
    }
}
