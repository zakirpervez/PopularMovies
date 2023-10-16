package com.husqvarna.popularmovies.ui.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.imageview.ShapeableImageView
import com.husqvarna.popularmovies.BuildConfig
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.api.models.response.ResultsItem
import com.husqvarna.popularmovies.databinding.MovieItemBinding
import java.util.Locale
import javax.inject.Inject

class MoviesAdapter @Inject constructor(): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val moviesList = mutableListOf<ResultsItem?>()
    private var itemClickListener: OnMovieItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = moviesList[position]
        movie?.apply {
            holder.binding.movie = this
            holder.binding.root.setOnClickListener { itemClickListener?.onMovieClick(this) }
            originalLanguage?.let { holder.binding.languageText.text = Locale(it).displayLanguage }
            loadPosterImage(holder.binding.moviePosterImage, posterPath)
        }
    }

    private fun loadPosterImage(moviePosterImage: ShapeableImageView, posterPath: String?) {
        val posterUrl = "${BuildConfig.IMAGES_URL}${posterPath}"
        val  glideRequestOptions = RequestOptions()
            .placeholder(R.drawable.baseline_image_24)
            .error(R.drawable.baseline_broken_image_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(moviePosterImage.context)
            .load(posterUrl)
            .apply(glideRequestOptions)
            .into(moviePosterImage)
    }

    fun updateMovies(movies: List<ResultsItem?>) {
        val oldSize = if (moviesList.isEmpty()) {
            0
        } else {
            moviesList.size
        }
        moviesList.addAll(movies)
        val newSize = moviesList.size
        notifyItemChanged(oldSize, newSize)
    }

    fun setOnMovieItemClickListener(listener: OnMovieItemClickListener) {
        itemClickListener = listener
    }
    class MoviesViewHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root)

    interface OnMovieItemClickListener {
        fun onMovieClick(movie: ResultsItem)
    }
}
