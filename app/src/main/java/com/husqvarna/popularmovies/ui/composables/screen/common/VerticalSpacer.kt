package com.husqvarna.popularmovies.ui.composables.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(width: Dp) {
    Spacer(
        modifier = Modifier
            .fillMaxHeight()
            .width(width)
            .background(Color.Transparent)
    )
}
