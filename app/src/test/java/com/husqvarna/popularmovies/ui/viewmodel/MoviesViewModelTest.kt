package com.husqvarna.popularmovies.ui.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.husqvarna.popularmovies.MockDataHelper
import com.husqvarna.popularmovies.api.Repository
import com.husqvarna.popularmovies.api.base.ERROR_UNKNOWN
import com.husqvarna.popularmovies.api.models.ApiResult
import com.husqvarna.popularmovies.api.models.response.PopularMoviesResponse
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MoviesViewModel
    private lateinit var repository: Repository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = MoviesViewModel(repository)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `fetchMovies with success response`() = runBlocking {
        coEvery { repository.fetchPopularMovies(1) } returns ApiResult.Success(MockDataHelper.getPopularMovieResponse())
        viewModel.fetchMovies(1)
        viewModel.moviesLiveData.observeForever {
            assert(it != null)
            assert(it?.size == 2)
        }
    }

    @Test
    fun `fetchMovies with error response`() = runBlocking {
        coEvery { repository.fetchPopularMovies(1) } returns ApiResult.Error(ERROR_UNKNOWN)
        viewModel.fetchMovies(1)
        viewModel.errorLiveData.observeForever {
            assert(it == ERROR_UNKNOWN)
        }
    }

    @Test
    fun `fetchMovies with empty response data`() = runBlocking {
        val expectedData = PopularMoviesResponse(results = emptyList())
        coEvery { repository.fetchPopularMovies(any()) } returns ApiResult.Success(expectedData)
        viewModel.fetchMovies(1)
        viewModel.moviesLiveData.observeForever {
            assert(it != null)
            assert(it?.size == 0)
        }
    }
}
