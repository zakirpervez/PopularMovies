package com.husqvarna.popularmovies.ui.fragments.home.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.husqvarna.popularmovies.BuildConfig
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.api.models.response.Movie
import com.husqvarna.popularmovies.databinding.MovieItemBinding
import com.husqvarna.popularmovies.util.loadImage
import java.util.Locale

class MoviesPagingAdapter :
    PagingDataAdapter<Movie, MoviesPagingAdapter.MoviesViewHolder>(MOVIE_DIFF_CALLBACK) {
    private var itemClickListener: OnMovieItemClickListener? = null

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.apply {
            holder.binding.movie = this
            holder.binding.root.setOnClickListener { itemClickListener?.onMovieClick(this) }
            originalLanguage?.let { holder.binding.languageText.text = Locale(it).displayLanguage }
            val posterUrl = "${BuildConfig.IMAGES_URL}${posterPath}"
            holder.binding.moviePosterImage.loadImage(posterUrl)
            setContentDescription(holder.binding, movie)
        }
    }

    /**
     * Setup content description according the display item.
     * @param binding [MovieItemBinding]
     * @param movie [Movie]
     */
    private fun setContentDescription(binding: MovieItemBinding, movie: Movie) {
        binding.moviePosterImage.contentDescription = movie.title
        val releaseCd =
            "${binding.releasedImage.context.getString(R.string.movie_item_released_cd)} ${movie.releaseDate}"
        binding.releasedImage.contentDescription = releaseCd
        movie.originalLanguage?.let {
            binding.languageText.contentDescription =
                "${binding.releasedImage.context.getString(R.string.movie_item_language_cd)} ${
                    Locale(it).displayLanguage
                }"
        }
    }

    fun setOnMovieItemClickListener(listener: OnMovieItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    inner class MoviesViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnMovieItemClickListener {
        fun onMovieClick(movie: Movie)
    }

    companion object {
        val MOVIE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}
