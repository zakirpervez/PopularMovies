package com.husqvarna.popularmovies.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(): BaseViewModel() {
    private val _loaderMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loaderLiveData: MutableLiveData<Boolean> = _loaderMutableLiveData

    fun showLoader(isLoading: Boolean) {
        _loaderMutableLiveData.postValue(isLoading)
    }
}
