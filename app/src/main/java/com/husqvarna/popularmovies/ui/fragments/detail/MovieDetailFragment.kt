package com.husqvarna.popularmovies.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.husqvarna.popularmovies.BuildConfig
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.databinding.FragmentMovieDetailBinding
import com.husqvarna.popularmovies.ui.fragments.detail.adapter.GeneresAdapter
import com.husqvarna.popularmovies.ui.fragments.detail.adapter.ProductionCompaniesAdapter
import com.husqvarna.popularmovies.ui.viewmodel.MovieDetailsViewModel
import com.husqvarna.popularmovies.util.loadImage
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    @Inject lateinit var generesAdapter: GeneresAdapter
    @Inject lateinit var productionCompaniesAdapter: ProductionCompaniesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("movieId")?.let { movieId ->
            movieDetailsViewModel.fetchMovieDetails(movieId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = movieDetailsViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setupViews()
    }

    private fun setupViews() {
        with(binding.genereRecyclerView) {
            itemAnimator = null
            layoutManager = LinearLayoutManager(
                requireContext(),
                androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = generesAdapter
        }

        with(binding.productionHousesRecyclerView) {
            itemAnimator = null
            layoutManager = LinearLayoutManager(
                requireContext(),
                androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = productionCompaniesAdapter
        }
    }

    private fun observeData() {
        movieDetailsViewModel.moviesDetailsLiveData.observe(viewLifecycleOwner) {
            movieDetailsViewModel.isLoading.set(false)
            movieDetailsViewModel.movieTitleMutableLiveData.value = it?.title
            movieDetailsViewModel.movieTagLineMutableLiveData.value = it?.tagline
            movieDetailsViewModel.releaseDateMutableLiveData.value = it?.releaseDate
            movieDetailsViewModel.overviewMutableLiveData.value = it?.overview
            movieDetailsViewModel.budgetMutableLiveData.value = it?.budget.toString()
            movieDetailsViewModel.imdbRatingMutableLiveData.value =
                "${getString(R.string.imdb_rating)}\n${it?.imdbId}"
            movieDetailsViewModel.popularityMutableLiveData.value =
                "${getString(R.string.popularity)}\n${it?.popularity}"
            it?.originalLanguage?.let { language ->
                movieDetailsViewModel.languageMutableLiveData.value =
                    Locale(language).displayLanguage
            }
            it?.backdropPath?.let { path ->
                val posterUrl = "${BuildConfig.IMAGES_URL}${path}"
                binding.moviePosterImage.loadImage(posterUrl)
            }
            generesAdapter.updateGeneres(it?.genres ?: emptyList())
            productionCompaniesAdapter.updateProductionCompanies(it?.productionCompanies ?: emptyList())
        }

        movieDetailsViewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
