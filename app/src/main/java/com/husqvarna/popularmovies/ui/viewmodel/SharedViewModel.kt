package com.husqvarna.popularmovies.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Shared view-model class for the view-models. Contains the common thing which is shared by fragments.
 */
@HiltViewModel
class SharedViewModel @Inject constructor() : BaseViewModel() {
    /**
     * Show or hide the loader.
     * @param isLoading true if loader is to be shown else false.
     */
    fun showLoader(isLoading: Boolean) {
        _loaderMutableLiveData.postValue(isLoading)
    }
}
