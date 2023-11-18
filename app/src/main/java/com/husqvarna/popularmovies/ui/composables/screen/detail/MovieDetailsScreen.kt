package com.husqvarna.popularmovies.ui.composables.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.husqvarna.popularmovies.BuildConfig
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.api.models.response.GenresItem
import com.husqvarna.popularmovies.api.models.response.MovieDetailsResponse
import com.husqvarna.popularmovies.api.models.response.ProductionCompaniesItem
import com.husqvarna.popularmovies.api.models.response.ProductionCountriesItem
import com.husqvarna.popularmovies.api.models.response.SpokenLanguagesItem
import com.husqvarna.popularmovies.ui.composables.common.ErrorData
import com.husqvarna.popularmovies.ui.composables.screen.common.Heading
import com.husqvarna.popularmovies.ui.composables.common.IconTextGroup
import com.husqvarna.popularmovies.ui.composables.common.MoviePosterImage
import com.husqvarna.popularmovies.ui.composables.common.Normal
import com.husqvarna.popularmovies.ui.composables.common.ProgressIndicator
import com.husqvarna.popularmovies.ui.composables.theme.Purple200
import com.husqvarna.popularmovies.ui.composables.theme.TurmericYellow
import com.husqvarna.popularmovies.ui.viewmodel.MovieDetailsViewModel
import java.util.*

@Composable
fun MovieDetailsScreen(movieId: Int, movieDetailsViewModel: MovieDetailsViewModel) {
    val movieDetailsState = movieDetailsViewModel.moviesDetailsLiveData.observeAsState(null)
    val loadingState = movieDetailsViewModel.loaderLiveData.observeAsState(null)
    val errorState = movieDetailsViewModel.errorLiveData.observeAsState(null)

    LaunchedEffect(key1 = Unit, block = {
        movieDetailsViewModel.fetchMovieDetails(movieId)
    })

    loadingState.value?.let {
        if (it) {
            ProgressIndicator()
        }
    }

    errorState.value?.let {
        ErrorData(
            title = stringResource(id = R.string.no_data_found),
            content = it,
            modifier = Modifier
                .fillMaxSize()
                .background(color = TurmericYellow)
        )
    }

    movieDetailsState.value?.let {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            item {
                MovieDetails(it)
            }
        }
    }
}

@Composable
fun MovieDetails(movieDetails: MovieDetailsResponse) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Purple200)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        val imageUrl = "${BuildConfig.IMAGES_URL}/${movieDetails.backdropPath}"
        MoviePosterImage(
            url = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(10f / 5f)

        )
        Heading(text = movieDetails.title ?: stringResource(id = R.string.dash))
        Normal(
            text = movieDetails.tagline ?: stringResource(id = R.string.dash),
            fontStyle = FontStyle.Italic
        )
        IconTextGroup(
            title = movieDetails.releaseDate ?: stringResource(id = R.string.dash),
            drawableId = R.drawable.baseline_calendar_month_24
        )
        movieDetails.originalLanguage?.let {
            IconTextGroup(
                title = Locale(it).displayLanguage,
                drawableId = R.drawable.baseline_language_24
            )
        }
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

@Preview
@Composable
fun MovieDetailsPreview() {
    val dummyMovieDetails = MovieDetailsResponse(
        originalLanguage = "en",
        imdbId = "tt1234567",
        video = true,
        title = "Dummy Movie",
        backdropPath = "/dummy_backdrop_path.jpg",
        revenue = 1000000000,
        genres = listOf(
            GenresItem(id = 1, name = "Action"),
            GenresItem(id = 2, name = "Drama")
            // Add more genres as needed
        ),
        popularity = 123.45,
        productionCountries = listOf(
            ProductionCountriesItem(iso31661 = "US", name = "United States")
            // Add more production countries as needed
        ),
        id = 123456,
        voteCount = 5000,
        budget = 50000000,
        overview = "This is a dummy movie overview.",
        originalTitle = "Dummy Original Title",
        runtime = 150,
        posterPath = "/dummy_poster_path.jpg",
        spokenLanguages = listOf(
            SpokenLanguagesItem(iso6391 = "en", name = "English"),
            SpokenLanguagesItem(iso6391 = "es", name = "Spanish")
            // Add more spoken languages as needed
        ),
        productionCompanies = listOf(
            ProductionCompaniesItem(id = 123, name = "Dummy Productions"),
            ProductionCompaniesItem(id = 456, name = "Fake Studios")
            // Add more production companies as needed
        ),
        releaseDate = "2023-11-01",
        voteAverage = 8.5,
        belongsToCollection = null,
        tagline = "An amazing dummy movie",
        adult = false,
        homepage = "https://dummy-movie.com",
        status = "Released"
    )
    MovieDetails(movieDetails = dummyMovieDetails)
}
