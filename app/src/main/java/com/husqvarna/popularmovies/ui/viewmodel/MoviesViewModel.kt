package com.husqvarna.popularmovies.ui.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.husqvarna.popularmovies.api.ApiException
import com.husqvarna.popularmovies.api.response.ResultsItem
import com.husqvarna.popularmovies.helper.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: Repository): BaseViewModel() {
    private val _moviesLiveData = MutableLiveData<List<ResultsItem?>?>()
    val moviesLiveData: LiveData<List<ResultsItem?>?> = _moviesLiveData
    val isLoading: ObservableBoolean = ObservableBoolean(false)

    fun fetchMovies(page: Int) = viewModelScope.launch(Dispatchers.IO) {
        try{
            isLoading.set(true)
            val response = repository.fetchPopularMovies(page)
            if (response.results.isNullOrEmpty()) {
                _errorLiveData.postValue("No movies found")
            } else {
                _moviesLiveData.postValue(response.results)
            }
        } catch (e: ApiException) {
            _errorLiveData.postValue(e.message)
        } finally {
            isLoading.set(false)
        }
    }
}
