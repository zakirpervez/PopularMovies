package com.husqvarna.popularmovies.ui.composables.screen.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.husqvarna.popularmovies.ui.composables.theme.DullBlack

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
