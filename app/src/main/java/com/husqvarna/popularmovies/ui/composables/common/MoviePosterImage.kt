package com.husqvarna.popularmovies.ui.composables.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.ui.composables.theme.DullBlack

@Composable
fun MoviePosterImage(url: String, modifier: Modifier) {
    AsyncImage(
        model = url,
        placeholder = painterResource(id = R.drawable.baseline_image_24),
        error = painterResource(id = R.drawable.baseline_broken_image_24),
        contentDescription = "Husqvarna Logo",
        modifier = modifier,
        contentScale = ContentScale.FillBounds,
    )
}

@Preview
@Composable
fun MoviePosterImagePreview() {
    MoviePosterImage(
        url = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
        modifier = Modifier
            .background(DullBlack)
            .aspectRatio(0.7f)
            .scale(0.8f)
    )
}
