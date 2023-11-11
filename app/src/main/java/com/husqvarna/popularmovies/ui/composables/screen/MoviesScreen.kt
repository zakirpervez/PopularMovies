package com.husqvarna.popularmovies.ui.composables.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.api.models.response.ResultsItem
import com.husqvarna.popularmovies.ui.composables.theme.DullBlack
import com.husqvarna.popularmovies.ui.composables.theme.Purple200

@Composable
fun MoviesScreen(onNavigate: (id: Int) -> Unit) {
    val movies = listOf(
        ResultsItem(
            title = "Movie 1", id = 1, originalLanguage = "English", releaseDate = "16/10/2021"
        ),
        ResultsItem(
            title = "Movie 2", id = 2, originalLanguage = "English", releaseDate = "16/10/2021"
        ),
        ResultsItem(
            title = "Movie 3", id = 3, originalLanguage = "English", releaseDate = "16/10/2021"
        ),
        ResultsItem(
            title = "Movie 4", id = 4, originalLanguage = "English", releaseDate = "16/10/2021"
        ),
        ResultsItem(
            title = "Movie 5", id = 5, originalLanguage = "English", releaseDate = "16/10/2021"
        ),
        ResultsItem(
            title = "Movie 6", id = 6, originalLanguage = "English", releaseDate = "16/10/2021"
        ),
        ResultsItem(
            title = "Movie 7", id = 7, originalLanguage = "English", releaseDate = "16/10/2021"
        ),
        ResultsItem(
            title = "Movie 8", id = 8, originalLanguage = "English", releaseDate = "16/10/2021"
        ),
        ResultsItem(
            title = "Movie 9", id = 9, originalLanguage = "English", releaseDate = "16/10/2021"
        ),
        ResultsItem(
            title = "Movie 10", id = 10, originalLanguage = "English", releaseDate = "16/10/2021"
        ),
        ResultsItem(
            title = "Movie 11", id = 11, originalLanguage = "English", releaseDate = "16/10/2021"
        ),
        ResultsItem(
            title = "Movie 12", id = 12, originalLanguage = "English", releaseDate = "16/10/2021"
        ),
    )

    Surface(
        modifier = Modifier
            .background(color = Purple200)
            .fillMaxWidth()
    ) {
        LazyColumn(modifier = Modifier.background(color = Purple200)) {
            items(movies.size) { index ->
                val movie = movies[index]
                MovieItem(movie = movie, onNavigate = onNavigate)
            }
        }
    }
}

@Composable
fun MovieItem(movie: ResultsItem, onNavigate: (id: Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .height(200.dp)
            .fillMaxWidth()
            .clickable { onNavigate(movie.id ?: 0) },
        colors = CardDefaults.cardColors(
            containerColor = Purple200,
        ),

        ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            DullBlackContainer()
            MovieContent(movie = movie)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MovieItem(movie = ResultsItem(
        title = "Avenger End Game", releaseDate = "16/10/2023", originalLanguage = "English"
    ), onNavigate = {})
}

@Composable
fun DullBlackContainer() {
    Box(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .aspectRatio(10f / 3.5f)
            .background(
                color = DullBlack,
                shape = RoundedCornerShape(8.dp)
            ),
    )
}

@Composable
fun MovieContent(movie: ResultsItem) {
    Row(
        modifier = Modifier
            .padding(start = 32.dp, end = 16.dp)
            .fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.husqvarna_logo_vector_white
            ),
            contentDescription = "Husqvarna Logo",
            modifier = Modifier
                .background(color = Color.Gray)
                .aspectRatio(10f / 20f, true)
                .scale(1f)
        )

        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(8.dp)
                .background(Color.Transparent)
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = movie.title ?: "",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                fontSize = 16.sp
            )
            MovieSubItem(title = movie.releaseDate ?: "-", drawableId = R.drawable.baseline_calendar_month_24)
            MovieSubItem(title = movie.originalLanguage ?: "-", drawableId = R.drawable.baseline_language_24)
        }
    }
}

@Composable
fun MovieSubItem(title: String, drawableId: Int) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(Color.Transparent)
    )
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = drawableId),
            contentDescription = "release date",
            tint = Color.White
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .width(8.dp)
                .background(Color.Transparent)
        )
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
    }
}

