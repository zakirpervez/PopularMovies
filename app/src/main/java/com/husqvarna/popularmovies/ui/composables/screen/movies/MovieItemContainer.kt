package com.husqvarna.popularmovies.ui.composables.screen.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.husqvarna.popularmovies.api.models.response.Movie

@Composable
fun MovieItemContainer(movie: Movie, onNavigate: (id: Int) -> Unit) {
    Box(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .height(200.dp)
            .fillMaxWidth()
            .clickable { onNavigate(movie.id ?: 0) }, contentAlignment = Alignment.Center
    ) {
        DullBlackContainer()
        MovieContentContainer(movie = movie)
    }
}

@Preview
@Composable
fun MovieItemContainerPreview() {
    MovieItemContainer(movie = Movie(
        title = "Avenger End Game", releaseDate = "16/10/2023", originalLanguage = "English"
    ), onNavigate = {})
}
