package com.husqvarna.popularmovies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.husqvarna.popularmovies.api.Repository
import com.husqvarna.popularmovies.api.models.ApiResult
import com.husqvarna.popularmovies.api.models.response.MovieDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View-model class for the movie details screen.
 */
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: Repository) :
    BaseViewModel() {
    private val moviesDetailsMutableLiveData = MutableLiveData<MovieDetailsResponse?>()
    val moviesDetailsLiveData: LiveData<MovieDetailsResponse?> = moviesDetailsMutableLiveData
    val movieTitleMutableLiveData = MutableLiveData<String>()
    val movieTagLineMutableLiveData = MutableLiveData<String>()
    val releaseDateMutableLiveData = MutableLiveData<String>()
    val languageMutableLiveData = MutableLiveData<String>()
    val budgetMutableLiveData = MutableLiveData<String>()
    val overviewMutableLiveData = MutableLiveData<String>()
    val imdbRatingMutableLiveData = MutableLiveData<String>()
    val popularityMutableLiveData = MutableLiveData<String>()

    /**
     * Fetch the movie details from the server.
     * @see [Repository.fetchMovieDetails]
     */
    fun fetchMovieDetails(movieId: Int) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = repository.fetchMovieDetails(movieId)) {
            is ApiResult.Success<MovieDetailsResponse> -> {
                moviesDetailsMutableLiveData.postValue(response.data)
            }

            is ApiResult.Error -> {
                errorMutableLiveData.postValue(response.errorMessage)
            }
        }
    }
}
