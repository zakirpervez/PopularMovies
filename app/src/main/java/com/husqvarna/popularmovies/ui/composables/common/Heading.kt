package com.husqvarna.popularmovies.ui.composables.screen.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun Heading(text: String, fontSize: TextUnit = 16.sp) {
    Text(
        text = text,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        fontSize = fontSize
    )
}

@Preview
@Composable
fun HeadingPreview() {
    Heading("Avenger Eng Game", 16.sp)
}
