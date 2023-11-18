package com.husqvarna.popularmovies.ui.composables.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.husqvarna.popularmovies.BuildConfig
import com.husqvarna.popularmovies.api.models.response.ProductionCompaniesItem
import com.husqvarna.popularmovies.ui.composables.common.MoviePosterImage
import com.husqvarna.popularmovies.ui.composables.common.Normal
import com.husqvarna.popularmovies.ui.composables.theme.TurmericYellow
import java.util.Locale

/**
 * Production houses list view.
 * @param companies [List] of [ProductionCompaniesItem]
 */
@Composable
fun ProductionHousesListView(companies: List<ProductionCompaniesItem?>) {
    LazyRow(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = companies.size,
            key = { index -> index }
        ) { item ->
            ProductionHousesItemView(company = companies[item]!!)
        }
    }
}

/**
 * Production houses item view.
 * @param company [ProductionCompaniesItem]
 */
@Composable
fun ProductionHousesItemView(company: ProductionCompaniesItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = TurmericYellow)
            .padding(4.dp)
    ) {
        MoviePosterImage(
            url = "${BuildConfig.IMAGES_URL}/${company.logoPath}",
            modifier = Modifier
                .padding(start = 4.dp, top = 8.dp, bottom = 8.dp, end = 4.dp)
                .aspectRatio(10f / 11f)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(start = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Normal(text = company.name ?: "")
            company.originCountry?.let {
                Normal(text = Locale(it).displayCountry)
            }
        }
    }
}

@Preview
@Composable
fun ProductionHousesListViewPreview() {
    val companies = listOf(
        ProductionCompaniesItem(id = 1, logoPath = "", name = "Marvel", originCountry = "usa"),
        ProductionCompaniesItem(id = 2, logoPath = "", name = "DC", originCountry = "usa"),
        ProductionCompaniesItem(id = 3, logoPath = "", name = "Marvel", originCountry = "usa"),
        ProductionCompaniesItem(id = 4, logoPath = "", name = "Marvel", originCountry = "usa"),
    )
    ProductionHousesListView(companies = companies)
}
