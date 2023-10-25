package com.husqvarna.popularmovies.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Base view-model class for the view-models. Contains the common thing which is used inside live data.
 */
abstract class BaseViewModel : ViewModel() {
    protected val loaderMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loaderLiveData: MutableLiveData<Boolean> = loaderMutableLiveData
    protected val errorMutableLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = errorMutableLiveData
}
