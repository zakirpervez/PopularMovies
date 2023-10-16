package com.husqvarna.popularmovies.ui.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.husqvarna.popularmovies.api.Repository
import com.husqvarna.popularmovies.api.models.ApiResult
import com.husqvarna.popularmovies.api.models.response.MovieDetailsResponse
import com.husqvarna.popularmovies.api.models.response.ResultsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {
    private val _moviesDetailsLiveData = MutableLiveData<MovieDetailsResponse?>()
    val moviesDetailsLiveData: LiveData<MovieDetailsResponse?> = _moviesDetailsLiveData
    val movieTitleMutableLiveData = MutableLiveData<String>()
    val movieTagLineMutableLiveData = MutableLiveData<String>()
    val releaseDateMutableLiveData = MutableLiveData<String>()
    val languageMutableLiveData = MutableLiveData<String>()
    val budgetMutableLiveData = MutableLiveData<String>()
    val overviewMutableLiveData = MutableLiveData<String>()
    val imdbRatingMutableLiveData = MutableLiveData<String>()
    val popularityMutableLiveData = MutableLiveData<String>()

    fun fetchMovieDetails(movieId: Int) = viewModelScope.launch(Dispatchers.IO) {
        isLoading.set(true)
        when (val response = repository.fetchMovieDetails(movieId)) {
            is ApiResult.Success<MovieDetailsResponse> -> {
                isLoading.set(false)
                _moviesDetailsLiveData.postValue(response.data)
            }
            is ApiResult.Error -> {
                isLoading.set(false)
                _errorLiveData.postValue(response.errorMessage)
            }
        }
    }
}
