package com.husqvarna.popularmovies.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.husqvarna.popularmovies.MockDataHelper
import com.husqvarna.popularmovies.api.Repository
import com.husqvarna.popularmovies.api.base.ERROR_UNKNOWN
import com.husqvarna.popularmovies.api.models.ApiResult
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
class MovieDetailsViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var repository: Repository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = MovieDetailsViewModel(repository)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `fetchMovieDetails with success response`() = runBlocking {
        coEvery { repository.fetchMovieDetails(496450) } returns ApiResult.Success(MockDataHelper.getMovieDetailsResponse())
        viewModel.fetchMovieDetails(496450)
        viewModel.moviesDetailsLiveData.observeForever {
            assert(it != null)
            assert(it?.id == 496450)
        }
    }

    @Test
    fun `fetchMovieDetails with error response`() = runBlocking {
        coEvery { repository.fetchMovieDetails(496450) } returns ApiResult.Error(ERROR_UNKNOWN)
        viewModel.fetchMovieDetails(496450)
        viewModel.errorLiveData.observeForever {
            assert(it == ERROR_UNKNOWN)
        }
    }


}
