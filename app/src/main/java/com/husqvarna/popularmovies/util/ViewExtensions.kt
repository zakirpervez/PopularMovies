package com.husqvarna.popularmovies.util

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView
import com.husqvarna.popularmovies.R

fun ShapeableImageView.loadImage(url: String) {
    val glideRequestOptions = RequestOptions()
        .placeholder(R.drawable.baseline_image_24)
        .error(R.drawable.baseline_broken_image_24)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
    Glide.with(context)
        .load(url)
        .apply(glideRequestOptions)
        .into(this)
}
