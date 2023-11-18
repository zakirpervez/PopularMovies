package com.husqvarna.popularmovies.ui.composables.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.husqvarna.popularmovies.R

/**
 * Icon text group. Used to show icon and text in a row.
 * @param title [String]
 * @param drawableId [Int]
 */
@Composable
fun IconTextGroup(title: String, drawableId: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = drawableId),
            contentDescription = stringResource(id = R.string.movie_item_released_cd),
            tint = Color.White
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .width(8.dp)
                .background(Color.Transparent)
        )
        Text(
            text = title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun IconTextGroupPreview() {
    IconTextGroup("Title", R.drawable.baseline_image_24)
}
