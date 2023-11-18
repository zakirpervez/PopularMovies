package com.husqvarna.popularmovies.ui.composables.screen.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.ui.composables.common.ErrorData
import com.husqvarna.popularmovies.ui.composables.common.ProgressIndicator
import com.husqvarna.popularmovies.ui.composables.theme.Purple200
import com.husqvarna.popularmovies.ui.composables.theme.TurmericYellow
import com.husqvarna.popularmovies.ui.viewmodel.MoviesViewModel

@Composable
fun MoviesScreen(moviesViewModel: MoviesViewModel, onNavigate: (id: Int) -> Unit) {
    val movies = moviesViewModel.movies.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Purple200),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (movies.loadState.refresh) {
            is LoadState.Loading -> {
                ProgressIndicator()
            }

            is LoadState.Error -> {
                val error = (movies.loadState.refresh as LoadState.Error).error.message
                    ?: stringResource(id = R.string.universal_error_message)
                ErrorData(
                    title = stringResource(id = R.string.no_data_found),
                    content = error,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = TurmericYellow)
                )
            }

            is LoadState.NotLoading -> {
                LazyColumn(modifier = Modifier.background(color = Purple200)) {
                    items(
                        count = movies.itemCount,
                        key = movies.itemKey { it.id ?: 0 }
                    ) { index ->
                        val movie = movies[index]!!
                        MovieItemContainer(movie = movie, onNavigate = onNavigate)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MoviesScreenPreview() {
    val moviesViewModel = hiltViewModel<MoviesViewModel>()
    MoviesScreen(
        moviesViewModel = moviesViewModel,
        onNavigate = {}
    )
}
