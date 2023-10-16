package com.husqvarna.popularmovies.api

import com.husqvarna.popularmovies.api.models.response.MovieDetailsResponse
import com.husqvarna.popularmovies.api.models.response.PopularMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("popular")
    suspend fun fetchPopularMovies(@Query("page") page: Int): Response<PopularMoviesResponse>

    @GET("{movie_id}")
    suspend fun fetchMovieDetails(@Path("movie_id") movieId: Int): Response<MovieDetailsResponse>
}
