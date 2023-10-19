package com.husqvarna.popularmovies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.husqvarna.popularmovies.api.Repository
import com.husqvarna.popularmovies.api.models.ApiResult
import com.husqvarna.popularmovies.api.models.response.PopularMoviesResponse
import com.husqvarna.popularmovies.api.models.response.ResultsItem
import com.husqvarna.popularmovies.ui.fragments.home.paging.MoviesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    private val _moviesLiveData = MutableLiveData<List<ResultsItem?>?>()
    val moviesLiveData: LiveData<List<ResultsItem?>?> = _moviesLiveData

    val movies =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2), pagingSourceFactory = {
            MoviesDataSource(repository)
        }).flow.cachedIn(viewModelScope)

    fun fetchMovies(page: Int) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = repository.fetchPopularMovies(page)) {
            is ApiResult.Success<PopularMoviesResponse> -> {
                _moviesLiveData.postValue(response.data.results)
            }

            is ApiResult.Error -> {
                _errorLiveData.postValue(response.errorMessage)
            }
        }
    }
}
