package com.husqvarna.popularmovies.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.husqvarna.popularmovies.api.Repository
import com.husqvarna.popularmovies.ui.fragments.home.paging.MoviesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: Repository) : BaseViewModel() {
    val movies =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2), pagingSourceFactory = {
            MoviesDataSource(repository, object : MoviesDataSource.ApiLoadingStateListener {
                override fun loadingState(isLoading: Boolean) {
                    loaderMutableLiveData.postValue(isLoading)
                }
            })
        }).flow.cachedIn(viewModelScope)
}
