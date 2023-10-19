package com.husqvarna.popularmovies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Base view-model class for the view-models. Contains the common thing which is used inside live data.
 */
abstract class BaseViewModel : ViewModel() {
    protected val _loaderMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loaderLiveData: MutableLiveData<Boolean> = _loaderMutableLiveData
    protected val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData
}
