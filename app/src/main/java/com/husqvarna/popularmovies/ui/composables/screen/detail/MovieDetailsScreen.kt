package com.husqvarna.popularmovies.ui.composables.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.husqvarna.popularmovies.BuildConfig
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.ui.composables.screen.common.Heading
import com.husqvarna.popularmovies.ui.composables.screen.common.IconTextGroup
import com.husqvarna.popularmovies.ui.composables.screen.common.MoviePosterImage
import com.husqvarna.popularmovies.ui.composables.screen.common.Normal
import com.husqvarna.popularmovies.ui.composables.screen.common.ProgressIndicator
import com.husqvarna.popularmovies.ui.composables.theme.Purple200
import com.husqvarna.popularmovies.ui.viewmodel.MovieDetailsViewModel
import java.util.*

@Composable
fun MovieDetailsScreen(movieId: Int, movieDetailsViewModel: MovieDetailsViewModel) {
    LazyColumn {
        item {
            MovieDetails(movieId = movieId, movieDetailsViewModel = movieDetailsViewModel)
        }
    }
}

@Composable
fun MovieDetails(movieId: Int, movieDetailsViewModel: MovieDetailsViewModel) {
    val movieDetailsState = movieDetailsViewModel.moviesDetailsLiveData.observeAsState(null)

    LaunchedEffect(key1 = Unit, block = {
        movieDetailsViewModel.fetchMovieDetails(movieId)
    })

    if (movieDetailsState.value == null) {
        ProgressIndicator()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Purple200)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            val movieDetails = movieDetailsState.value!!
            val imageUrl = "${BuildConfig.IMAGES_URL}/${movieDetails.backdropPath}"
            MoviePosterImage(
                url = imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(10f / 5f)

            )
            Heading(text = movieDetails.title ?: "")
            Normal(
                text = movieDetails.tagline ?: "",
                fontStyle = FontStyle.Italic
            )
            IconTextGroup(
                title = movieDetails.releaseDate ?: "",
                drawableId = R.drawable.baseline_calendar_month_24
            )
            IconTextGroup(
                title = Locale(movieDetails.originalLanguage ?: "en").displayLanguage,
                drawableId = R.drawable.baseline_language_24
            )
            IconTextGroup(
                title = movieDetails.budget.toString(),
                drawableId = R.drawable.baseline_monetization_on_24
            )
            Normal(text = movieDetails.overview ?: "")
            BoxTextPair(
                text1 = "IMDB Rating\n${movieDetails.imdbId}",
                text2 = "Popularity\n${movieDetails.popularity}"
            )
            Heading(text = "Generes")
            GeneresListView(genereList = movieDetails.genres ?: emptyList())
            Heading(text = "Production Houses")
            ProductionHousesListView(companies = movieDetails.productionCompanies ?: emptyList())
        }
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(1, hiltViewModel())
}
