package com.husqvarna.popularmovies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.husqvarna.popularmovies.ui.composables.screen.AppRoutes
import com.husqvarna.popularmovies.ui.composables.theme.PopularMoviesTheme
import dagger.hilt.android.AndroidEntryPoint


/**
 * Main activity for the app. Represent the single activity architecture.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PopularMoviesTheme {
                AppRoutes()
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AppRoutes()
}
