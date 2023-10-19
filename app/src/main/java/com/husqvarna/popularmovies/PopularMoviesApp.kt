package com.husqvarna.popularmovies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Application class for the app.
 */
@HiltAndroidApp
class PopularMoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize the timber library.
        Timber.plant(Timber.DebugTree())
    }
}
