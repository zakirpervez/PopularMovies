package com.husqvarna.popularmovies.api

import com.husqvarna.popularmovies.MockDataHelper
import com.husqvarna.popularmovies.api.base.ERROR_UNKNOWN
import com.husqvarna.popularmovies.api.models.ApiResult
import com.husqvarna.popularmovies.api.source.ApiDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RepositoryTest {
    private val apiDataSource = mockk<ApiDataSource>()
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = Repository(apiDataSource)
    }

    @Test
    fun `test fetchPopularMovies success`() = runBlocking {
        coEvery { apiDataSource.fetchPopularMovies(any()) } returns ApiResult.Success(MockDataHelper.getPopularMovieResponse())
        val result = repository.fetchPopularMovies(1)
        assert(result is ApiResult.Success)
    }

    @Test
    fun `test fetchPopularMovies failure`() = runBlocking {
        coEvery { apiDataSource.fetchPopularMovies(1) } returns ApiResult.Error(ERROR_UNKNOWN)
        val result = repository.fetchPopularMovies(1)
        assert(result is ApiResult.Error)
    }

    @Test
    fun `test fetchMovieDetails success`() = runBlocking {
        coEvery { apiDataSource.fetchMovieDetails(12345) } returns ApiResult.Success(MockDataHelper.getMovieDetailsResponse())
        val result = repository.fetchMovieDetails(12345)
        assert(result is ApiResult.Success)
    }

    @Test
    fun `test fetchMovieDetails failure`() = runBlocking {
        coEvery { apiDataSource.fetchMovieDetails(12345) } returns ApiResult.Error(ERROR_UNKNOWN)
        val result = repository.fetchMovieDetails(12345)
        assert(result is ApiResult.Error)
    }
}
