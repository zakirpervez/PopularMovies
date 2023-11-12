package com.husqvarna.popularmovies.ui.composables.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.husqvarna.popularmovies.ui.composables.screen.common.Normal
import com.husqvarna.popularmovies.ui.composables.theme.DullBlack

@Composable
fun RoundedChipsListView(chips: List<String>) {
    LazyRow(
        modifier = Modifier.height(48.dp)
    ) {
        items(
            count = chips.size,
            key = { index -> index }
        ) { item ->
            RoundedChip(text = chips[item])
        }
    }
}

@Composable
fun RoundedChip(text: String) {
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
           Normal(text = text)
        }
    }
}

@Preview
@Composable
fun RoundedChipsListViewPreview() {
    val sampleChips = listOf("Android", "Compose", "Jetpack", "Kotlin", "Feather")
    RoundedChipsListView(chips = sampleChips)
}
