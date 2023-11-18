package com.husqvarna.popularmovies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.ui.composables.common.ErrorData
import com.husqvarna.popularmovies.ui.composables.connectivity.ConnectivityObserver
import com.husqvarna.popularmovies.ui.composables.connectivity.NetworkConnectivityObserver
import com.husqvarna.popularmovies.ui.composables.screen.AppRoutes
import com.husqvarna.popularmovies.ui.composables.theme.PopularMoviesTheme
import com.husqvarna.popularmovies.ui.composables.theme.TurmericYellow
import dagger.hilt.android.AndroidEntryPoint


/**
 * Main activity for the app. Represent the single activity architecture.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            PopularMoviesTheme {
                val connectivityState by connectivityObserver.onConnectivityStatusChange()
                    .collectAsState(initial = ConnectivityObserver.Status.UNAVAILABLE)
                when (connectivityState) {
                    ConnectivityObserver.Status.AVAIlABLE, ConnectivityObserver.Status.LOSING -> {
                        AppRoutes()
                    }

                    ConnectivityObserver.Status.UNAVAILABLE, ConnectivityObserver.Status.LOST -> {
                        ErrorData(
                            title = "No Data Connectivity",
                            content = "Please connect to internet and try again",
                            drawableId = R.drawable.baseline_signal_wifi_connected_no_internet_4_24,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = TurmericYellow)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AppRoutes()
}
