package com.husqvarna.popularmovies.ui.composables.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.api.models.response.GenresItem
import com.husqvarna.popularmovies.ui.composables.common.Normal
import com.husqvarna.popularmovies.ui.composables.theme.DullBlack

/**
 * Genres list view.
 * @param genereList [List] of [GenresItem]
 */
@Composable
fun GeneresListView(genereList: List<GenresItem?>) {
    LazyRow(
        modifier = Modifier.height(48.dp)
    ) {
        items(
            count = genereList.size,
            key = { index -> index }
        ) { item ->
            GenereItemView(genresItem = genereList[item]!!)
        }
    }
}

/**
 * Genres item view.
 * @param genresItem [GenresItem]
 */
@Composable
fun GenereItemView(genresItem: GenresItem) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxHeight()
            .background(
                color = DullBlack,
                shape = MaterialTheme.shapes.large
            ),
        colors = CardDefaults.cardColors(
            containerColor = DullBlack
        )
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Normal(text = genresItem.name ?: stringResource(id = R.string.dash))
        }
    }
}

@Preview
@Composable
fun GeneresListViewPreview() {
    val sampleChips = listOf(
        GenresItem(name = "Action", id = 1),
        GenresItem(name = "Science Fictions", id = 2),
        GenresItem(name = "Action", id = 3),
        GenresItem(name = "Action", id = 4),
        GenresItem(name = "Action", id = 5),
        GenresItem(name = "Action", id = 6),
    )
    GeneresListView(genereList = sampleChips)
}
