package com.husqvarna.popularmovies.ui.fragments.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.husqvarna.popularmovies.api.Repository
import com.husqvarna.popularmovies.api.models.ApiResult
import com.husqvarna.popularmovies.api.models.response.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import javax.inject.Inject

/**
 * [MoviesDataSource] is the data source class for the movies list.
 */
class MoviesDataSource @Inject constructor(private val repository: Repository, private val apiLoadingListener: ApiLoadingStateListener? = null) :
    PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        apiLoadingListener?.loadingState(true)
        return try {
            val nextPageNumber = params.key ?: 1
            val apiResponse = CoroutineScope(Dispatchers.IO+Job()).async { repository.fetchPopularMovies(nextPageNumber) }
            val response = apiResponse.await()
            apiLoadingListener?.loadingState(false)
            when(response){
                is ApiResult.Success -> {
                    val data = response.data
                    LoadResult.Page(
                        data = data.results!!,
                        prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                        nextKey = if (nextPageNumber < data.totalPages!!) nextPageNumber + 1 else null
                    )
                }

                is ApiResult.Error -> {
                    LoadResult.Error(Exception(response.errorMessage))
                }
            }
        } catch (e: Exception) {
            apiLoadingListener?.loadingState(false)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    /**
     * Interface to listen to the loading state of the API.
     */
    interface ApiLoadingStateListener {
        fun loadingState(isLoading: Boolean)
    }
}
