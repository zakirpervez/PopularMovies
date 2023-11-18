package com.husqvarna.popularmovies.ui.composables.common

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.husqvarna.popularmovies.ui.composables.screen.common.Heading

@Composable
fun BoxText(text: String, modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Heading(text = text)
    }
}
