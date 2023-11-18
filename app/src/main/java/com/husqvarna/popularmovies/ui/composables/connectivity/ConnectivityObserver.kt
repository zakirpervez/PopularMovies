package com.husqvarna.popularmovies.ui.composables.connectivity

import kotlinx.coroutines.flow.Flow


interface ConnectivityObserver {
    fun onConnectivityStatusChange(): Flow<Status>

    enum class Status {
        AVAIlABLE, UNAVAILABLE, LOSING, LOST
    }
}
