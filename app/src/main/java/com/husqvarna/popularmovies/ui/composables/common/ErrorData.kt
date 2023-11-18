package com.husqvarna.popularmovies.ui.composables.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.ui.composables.screen.common.Heading
import com.husqvarna.popularmovies.ui.composables.screen.common.HorizontalSpacer
import com.husqvarna.popularmovies.ui.composables.screen.common.Normal
import com.husqvarna.popularmovies.ui.composables.theme.TurmericYellow

@Composable
fun ErrorData(
    title: String,
    content: String,
    @DrawableRes drawableId: Int = R.drawable.baseline_playlist_remove_24,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .background(color = Color.White)
                .width(200.dp)
                .aspectRatio(10f / 6f),
            painter = painterResource(id = drawableId),
            contentDescription = "No Data Found"
        )
        HorizontalSpacer(height = 16.dp)
        Heading(text = title, fontSize = 24.sp)
        HorizontalSpacer(height = 16.dp)
        Normal(
            text = content,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun NoDataPreview() {
    ErrorData(
        title = "Title",
        content = "Content",
        modifier = Modifier
            .fillMaxSize()
            .background(color = TurmericYellow)
    )
}