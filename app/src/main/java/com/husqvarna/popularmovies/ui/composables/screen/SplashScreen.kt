package com.husqvarna.popularmovies.ui.composables.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.ui.composables.theme.Purple200
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        onNavigate()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Purple200),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.husqvarna_logo_vector_white
            ),
            contentDescription = "Husqvarna Logo",
            modifier = Modifier
                .aspectRatio(0.4f, true)
                .scale(1f)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(onNavigate = {})
}
