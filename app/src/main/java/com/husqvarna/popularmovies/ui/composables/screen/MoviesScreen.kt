package com.husqvarna.popularmovies.ui.composables.screen

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.husqvarna.popularmovies.BuildConfig
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.api.models.response.Movie
import com.husqvarna.popularmovies.ui.composables.theme.DullBlack
import com.husqvarna.popularmovies.ui.composables.theme.Purple200
import com.husqvarna.popularmovies.ui.composables.theme.Purple40
import com.husqvarna.popularmovies.ui.viewmodel.MoviesViewModel

@Composable
fun MoviesScreen(moviesViewModel: MoviesViewModel, onNavigate: (id: Int) -> Unit) {

    val movies = moviesViewModel.movies.collectAsLazyPagingItems()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Purple200)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(color = Purple200),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (movies.loadState.refresh == LoadState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Purple200),
                    contentAlignment = Alignment.Center,
                ) {
                val strokeWidth = 4.dp
                CircularProgressIndicator(
                    modifier = Modifier
                        .drawBehind {
                            this.drawCircle(
                                Purple40,
                                radius = size.width / 2 - strokeWidth.toPx() / 2,
                                style = Stroke(strokeWidth.toPx())
                            )
                        }
                        .background(color = Purple200)
                        .padding(8.dp),
                    color = Color.White,
                    strokeWidth = strokeWidth,
                )
            }
        }

        LazyColumn(modifier = Modifier.background(color = Purple200)) {
            items(
                count = movies.itemCount,
                key = movies.itemKey { it.id ?: 0 }
            ) { index ->
                val movie = movies[index]!!
                MovieItem(movie = movie, onNavigate = onNavigate)
            }
        }
    }
}
}

@Composable
fun MovieItem(movie: Movie, onNavigate: (id: Int) -> Unit) {
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
fun MovieContent(movie: Movie) {
    Row(
        modifier = Modifier
            .padding(start = 32.dp, end = 16.dp)
            .fillMaxWidth(),
    ) {
        val posterUrl = "${BuildConfig.IMAGES_URL}${movie.posterPath}"
        AsyncImage(
            model = posterUrl,
            placeholder = painterResource(id = R.drawable.baseline_image_24),
            error = painterResource(id = R.drawable.baseline_broken_image_24),
            contentDescription = "Husqvarna Logo",
            modifier = Modifier
                .background(color = DullBlack)
                .aspectRatio(10f / 17f, true)
                .scale(1f),
            contentScale = ContentScale.FillHeight,
        )

        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(12.dp)
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
            MovieSubItem(
                title = movie.releaseDate ?: "-",
                drawableId = R.drawable.baseline_calendar_month_24
            )
            MovieSubItem(
                title = movie.originalLanguage ?: "-",
                drawableId = R.drawable.baseline_language_24
            )
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
    Row(
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

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MovieItem(movie = Movie(
        title = "Avenger End Game", releaseDate = "16/10/2023", originalLanguage = "English"
    ), onNavigate = {})
}
