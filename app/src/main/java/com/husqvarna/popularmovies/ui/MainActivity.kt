package com.husqvarna.popularmovies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.ui.composables.common.ErrorData
import com.husqvarna.popularmovies.ui.util.connectivity.ConnectivityObserver
import com.husqvarna.popularmovies.ui.util.connectivity.NetworkConnectivityObserver
import com.husqvarna.popularmovies.ui.composables.screen.AppRoutes
import com.husqvarna.popularmovies.ui.composables.theme.PopularMoviesTheme
import com.husqvarna.popularmovies.ui.composables.theme.TurmericYellow
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


/**
 * Main activity for the app. Represent the SPA architecture.
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

                    ConnectivityObserver.Status.LOST -> {
                        ErrorData(
                            title = stringResource(id = R.string.no_internet_connectivity_title),
                            content = stringResource(id = R.string.no_internet_connectivity_content),
                            drawableId = R.drawable.baseline_signal_wifi_connected_no_internet_4_24,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = TurmericYellow)
                        )
                    }

                    else -> Timber.d("Connectivity state: $connectivityState")
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
