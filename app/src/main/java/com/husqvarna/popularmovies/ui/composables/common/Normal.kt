package com.husqvarna.popularmovies.ui.composables.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Regular text view with normal text.
 * @param text [String]
 * @param fontSize [TextUnit]
 * @param fontWeight [FontWeight]
 * @param fontStyle [FontStyle]
 * @param textAlign [TextAlign]
 * @see Text
 */
@Composable
fun Normal(
    text: String,
    fontSize: TextUnit = 12.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    fontStyle: FontStyle = FontStyle.Normal,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        color = Color.White,
        fontWeight = fontWeight,
        fontSize = fontSize,
        fontStyle = fontStyle,
        textAlign = textAlign,
    )
}

@Preview
@Composable
fun NormalPreview() {
    Normal(
        text = "Avenger Eng Game",
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
    )
}
