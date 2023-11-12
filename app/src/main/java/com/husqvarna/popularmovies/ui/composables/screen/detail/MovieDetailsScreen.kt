package com.husqvarna.popularmovies.ui.composables.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.api.Repository
import com.husqvarna.popularmovies.api.models.response.ProductionCompaniesItem
import com.husqvarna.popularmovies.ui.composables.screen.common.Heading
import com.husqvarna.popularmovies.ui.composables.screen.common.HorizontalSpacer
import com.husqvarna.popularmovies.ui.composables.screen.common.IconTextGroup
import com.husqvarna.popularmovies.ui.composables.screen.common.MoviePosterImage
import com.husqvarna.popularmovies.ui.composables.screen.common.Normal
import com.husqvarna.popularmovies.ui.composables.theme.Purple200
import com.husqvarna.popularmovies.ui.viewmodel.MovieDetailsViewModel
import com.husqvarna.popularmovies.ui.viewmodel.MoviesViewModel


val companies = listOf(
    ProductionCompaniesItem(id = 1, logoPath = "", name = "Marvel", originCountry = "usa"),
    ProductionCompaniesItem(id = 2, logoPath = "", name = "DC", originCountry = "usa"),
    ProductionCompaniesItem(id = 3, logoPath = "", name = "Marvel", originCountry = "usa"),
    ProductionCompaniesItem(id = 4, logoPath = "", name = "Marvel", originCountry = "usa"),
)
@Composable
fun MovieDetailsScreen(movieId: Int /*movieDetailsViewModel: MovieDetailsViewModel*/) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Purple200)
            .padding(16.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        MoviePosterImage(
            url = "url",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(10f / 5f)

        )
        Heading(text = "Avenger Eng Game")
        Normal(text = "tag line", fontStyle = FontStyle.Italic)
        IconTextGroup(
            title = "21/01/1993",
            drawableId = R.drawable.baseline_calendar_month_24
        )
        IconTextGroup(
            title = "English",
            drawableId = R.drawable.baseline_language_24
        )
        IconTextGroup(
            title = "70000000",
            drawableId = R.drawable.baseline_monetization_on_24
        )
        Normal(text = "In this example, the ItalicText composable function takes a text parameter and applies the italic style to it using the buildAnnotatedString function. The withStyle function is used to apply the SpanStyle with italic font style. You can customize the")
        BoxTextPair(text1 = "IMDB Rating tt23468", text2 = "Popularity 123456")
        Heading(text = "Generes")
        RoundedChipsListView(chips = listOf("Android", "Compose", "Jetpack", "Kotlin", "Feather"))
        Heading(text = "Production Houses")
        BoxTextListView(companies = companies)
    }
}

@Preview
@Composable
fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(1)
}
