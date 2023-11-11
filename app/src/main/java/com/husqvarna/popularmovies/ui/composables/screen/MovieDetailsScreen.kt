package com.husqvarna.popularmovies.ui.composables.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailsScreen(movieId: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Movies Details Screen $movieId")
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(1)
}
