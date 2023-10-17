package com.husqvarna.popularmovies.api.source

import com.husqvarna.popularmovies.MockDataHelper
import com.husqvarna.popularmovies.api.ApiService
import com.husqvarna.popularmovies.api.models.ApiResult
import com.husqvarna.popularmovies.api.models.response.PopularMoviesResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

class ApiDataSourceTest {
    private val apiService = mockk<ApiService>()
    private val apiDataSource = spyk(ApiDataSource(apiService))

    @Test
    fun `fetchPopularMovies should return success`() = runBlocking {
        val mockResponse = MockDataHelper.getPopularMovieResponse()
        coEvery { apiService.fetchPopularMovies(1) } returns Response.success(mockResponse)
        val result = apiDataSource.fetchPopularMovies(1)
        coVerify { apiService.fetchPopularMovies(1) }
        assert(result is ApiResult.Success)
        assert((result as ApiResult.Success<PopularMoviesResponse>).data.page == 1)
        assert(result.data.totalPages == 1)
        assert(result.data.totalResults == 2)
        assert(result.data.results?.size == 2)
    }

    @Test
    fun `fetchPopularMovies should return error`() = runBlocking {
        coEvery { apiService.fetchPopularMovies(1) } returns Response.error(
            404,
            "Not Found".toResponseBody()
        )
        val result = apiDataSource.fetchPopularMovies(1)
        coVerify { apiService.fetchPopularMovies(1) }
        assert(result is ApiResult.Error)
        assert((result as ApiResult.Error).errorMessage.contains("404"))
    }

    @Test
    fun `fetchMovieDetails should return success`() = runBlocking {
        coEvery { apiService.fetchMovieDetails(12345) } returns Response.success(MockDataHelper.getMovieDetailsResponse())
        val result = apiDataSource.fetchMovieDetails(12345)
        coVerify { apiService.fetchMovieDetails(12345) }
        assert(result is ApiResult.Success)
    }

    @Test
    fun `fetchMovieDetails should return error`() = runBlocking {
        coEvery { apiService.fetchMovieDetails(any()) } returns Response.error(
            404,
            "Not Found".toResponseBody())
        val result = apiDataSource.fetchMovieDetails(12345)
        coVerify { apiService.fetchMovieDetails(12345) }
        assert(result is ApiResult.Error)
    }
}
