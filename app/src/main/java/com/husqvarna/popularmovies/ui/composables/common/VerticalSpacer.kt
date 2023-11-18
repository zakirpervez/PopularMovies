package com.husqvarna.popularmovies.ui.composables.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpacer(width: Dp) {
    Spacer(
        modifier = Modifier
            .fillMaxHeight()
            .width(width)
            .background(Color.Transparent)
    )
}
