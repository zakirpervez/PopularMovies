package com.husqvarna.popularmovies.ui.composables.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.husqvarna.popularmovies.ui.composables.common.BoxText
import com.husqvarna.popularmovies.ui.composables.common.VerticalSpacer
import com.husqvarna.popularmovies.ui.composables.theme.TurmericYellow

/**
 * Box text pair. Shows two text in a row with equal width and height along with turmeric yellow
 * background and white text.
 * @param text1 [String]
 * @param text2 [String]
 */
@Composable
fun BoxTextPair(text1: String, text2: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BoxText(
            text = text1, modifier = Modifier
                .weight(0.48f)
                .fillMaxHeight()
                .background(color = TurmericYellow)
        )
        VerticalSpacer(width = 12.dp)
        BoxText(
            text = text2, modifier = Modifier
                .weight(0.48f)
                .fillMaxHeight()
                .background(color = TurmericYellow)
        )
    }
}

@Preview
@Composable
fun BoxTextPairPreview() {
    BoxTextPair(
        "Mission Impossible",
        "Avenger Eng Game"
    )
}
