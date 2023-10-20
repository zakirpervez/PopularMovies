package com.husqvarna.popularmovies.ui.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.husqvarna.popularmovies.MockDataHelper
import com.husqvarna.popularmovies.api.Repository
import com.husqvarna.popularmovies.api.models.ApiResult
import com.husqvarna.popularmovies.api.models.response.ResultsItem
import com.husqvarna.popularmovies.ui.fragments.home.paging.MoviesDataSource
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
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

    private val popularMoviesResponse = MockDataHelper.getPopularMovieResponse()

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
    fun `movies LiveData is populated with PagingData`() = runBlocking {
        val pagingSource = mockk<MoviesDataSource>()
        val results = popularMoviesResponse.results!!

        coEvery { repository.fetchPopularMovies(1) } returns ApiResult.Success(popularMoviesResponse)
        coEvery { pagingSource.load(any()) } returns PagingSource.LoadResult.Page(data = results, prevKey = null, nextKey = 2)

        val moviesFlow: Flow<PagingData<ResultsItem>> = viewModel.movies
        assertNotNull(moviesFlow)
    }
}
