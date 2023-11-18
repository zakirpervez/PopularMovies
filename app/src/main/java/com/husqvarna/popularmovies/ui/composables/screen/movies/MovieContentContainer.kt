package com.husqvarna.popularmovies.ui.composables.screen.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.husqvarna.popularmovies.BuildConfig
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.api.models.response.Movie
import com.husqvarna.popularmovies.ui.composables.screen.common.Heading
import com.husqvarna.popularmovies.ui.composables.common.HorizontalSpacer
import com.husqvarna.popularmovies.ui.composables.common.IconTextGroup
import com.husqvarna.popularmovies.ui.composables.common.MoviePosterImage
import com.husqvarna.popularmovies.ui.composables.common.VerticalSpacer
import com.husqvarna.popularmovies.ui.composables.theme.DullBlack
import java.util.*

@Composable
fun MovieContentContainer(movie: Movie) {
    Row(
        modifier = Modifier
            .padding(start = 32.dp, end = 16.dp)
            .fillMaxWidth(),
    ) {
        val posterUrl = "${BuildConfig.IMAGES_URL}${movie.posterPath}"
        MoviePosterImage(
            url = posterUrl, modifier = Modifier
                .background(color = DullBlack)
                .aspectRatio(10f / 18f, true)
                .scale(1f)
        )

        VerticalSpacer(width = 12.dp)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            Heading(text = movie.title ?: stringResource(id = R.string.dash))
            HorizontalSpacer(height = 8.dp)
            IconTextGroup(
                title = movie.releaseDate ?: stringResource(id = R.string.dash),
                drawableId = R.drawable.baseline_calendar_month_24
            )
            HorizontalSpacer(height = 8.dp)
            movie.originalLanguage?.let {
                IconTextGroup(
                    title = Locale(it).displayLanguage,
                    drawableId = R.drawable.baseline_language_24
                )
            }

        }
    }
}

@Preview
@Composable
fun MovieContentContainerPreview() {
    MovieContentContainer(
        movie = Movie(
            title = "Avenger End Game", releaseDate = "16/10/2023", originalLanguage = "English"
        )
    )
}
