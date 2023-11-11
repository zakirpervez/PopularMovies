package com.husqvarna.popularmovies.ui.fragments.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.husqvarna.popularmovies.MockDataHelper
import com.husqvarna.popularmovies.api.Repository
import com.husqvarna.popularmovies.api.base.ERROR_UNKNOWN
import com.husqvarna.popularmovies.api.models.ApiResult
import com.husqvarna.popularmovies.api.models.response.PopularMoviesResponse
import com.husqvarna.popularmovies.api.models.response.Movie
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class MoviesDataSourceTest {

    private val popularMoviesResponse = MockDataHelper.getPopularMovieResponse()
    private val repository = mockk<Repository>()
    private val apiLoadingListener = mockk<MoviesDataSource.ApiLoadingStateListener>()
    private val moviesDataSource = MoviesDataSource(repository, apiLoadingListener)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { apiLoadingListener.loadingState(true) } returns Unit
        coEvery { apiLoadingListener.loadingState(false) } returns Unit
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `load returns LoadResult Page when API call is successful`() = runBlocking {
        val params = PagingSource.LoadParams.Refresh(1, 20, false)
        val expectedResponse = mockk<ApiResult.Success<PopularMoviesResponse>>()
        coEvery { repository.fetchPopularMovies(1) } returns ApiResult.Success(popularMoviesResponse)
        coEvery { expectedResponse.data } returns mockk()
        coEvery { expectedResponse.data.results } returns emptyList()
        coEvery { expectedResponse.data.totalPages } returns 1

        val result = moviesDataSource.load(params)

        coVerify(exactly = 1) { repository.fetchPopularMovies(1) }
        assert(result is PagingSource.LoadResult.Page)
    }

    @Test
    fun `load returns LoadResult Error when API call returns an error`() = runBlocking {
        val params = PagingSource.LoadParams.Refresh(1, 20, false)
        val expectedResponse = ApiResult.Error(ERROR_UNKNOWN)
        coEvery { repository.fetchPopularMovies(1) } returns expectedResponse
        val result = moviesDataSource.load(params)

        coVerify(exactly = 1) { repository.fetchPopularMovies(1) }
        assert(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun `getRefreshKey returns anchorPosition from PagingState`() {
        val pagingState = mockk<PagingState<Int, Movie>>()
        every { pagingState.anchorPosition } returns 10
        val refreshKey = moviesDataSource.getRefreshKey(pagingState)
        assertEquals(10, refreshKey)
    }
}
