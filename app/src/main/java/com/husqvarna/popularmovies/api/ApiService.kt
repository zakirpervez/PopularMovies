package com.husqvarna.popularmovies.api

import com.husqvarna.popularmovies.api.models.response.MovieDetailsResponse
import com.husqvarna.popularmovies.api.models.response.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API service.
 */
interface ApiService {
    /** Fetch the popular movies from the API. */
    @GET("popular")
    suspend fun fetchPopularMovies(@Query("page") page: Int): Response<PopularMoviesResponse>

    /** Fetch the movie details from the API. */
    @GET("{movie_id}")
    suspend fun fetchMovieDetails(@Path("movie_id") movieId: Int): Response<MovieDetailsResponse>
}
