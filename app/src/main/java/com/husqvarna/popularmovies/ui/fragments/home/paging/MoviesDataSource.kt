package com.husqvarna.popularmovies.ui.fragments.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.husqvarna.popularmovies.api.Repository
import com.husqvarna.popularmovies.api.models.ApiResult
import com.husqvarna.popularmovies.api.models.response.ResultsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesDataSource @Inject constructor(private val repository: Repository) :
    PagingSource<Int, ResultsItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            val apiResponse = CoroutineScope(Dispatchers.IO+Job()).async { repository.fetchPopularMovies(nextPageNumber) }
            val response = apiResponse.await()
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
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ResultsItem>): Int? {
        return state.anchorPosition
    }
}
