package com.husqvarna.popularmovies.ui.util.connectivity

import kotlinx.coroutines.flow.Flow


interface ConnectivityObserver {
    fun onConnectivityStatusChange(): Flow<Status>

    enum class Status {
        AVAIlABLE, UNAVAILABLE, LOSING, LOST
    }
}
