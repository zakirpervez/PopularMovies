package com.husqvarna.popularmovies.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.husqvarna.popularmovies.BuildConfig
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.databinding.FragmentMovieDetailBinding
import com.husqvarna.popularmovies.ui.fragments.detail.adapter.GeneresAdapter
import com.husqvarna.popularmovies.ui.fragments.detail.adapter.ProductionCompaniesAdapter
import com.husqvarna.popularmovies.ui.viewmodel.MovieDetailsViewModel
import com.husqvarna.popularmovies.ui.viewmodel.SharedViewModel
import com.husqvarna.popularmovies.util.loadImage
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

/**
 * [MovieDetailFragment] represent the movie details screen.
 */
@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    @Inject
    lateinit var generesAdapter: GeneresAdapter

    @Inject
    lateinit var productionCompaniesAdapter: ProductionCompaniesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("movieId")?.let { movieId ->
            sharedViewModel.showLoader(true)
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

    /**
     * Setup the views.
     */
    private fun setupViews() {
        with(binding.genereRecyclerView) {
            itemAnimator = null
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = generesAdapter
        }

        with(binding.productionHousesRecyclerView) {
            itemAnimator = null
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = productionCompaniesAdapter
        }
    }

    /**
     * Observe the data from the view-model.
     */
    private fun observeData() {
        movieDetailsViewModel.moviesDetailsLiveData.observe(viewLifecycleOwner) {
            handleVisibility(true)
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
            productionCompaniesAdapter.updateProductionCompanies(
                it?.productionCompanies ?: emptyList()
            )
            sharedViewModel.showLoader(false)
        }

        movieDetailsViewModel.errorLiveData.observe(viewLifecycleOwner) {
            sharedViewModel.showLoader(false)
            handleVisibility(false)
        }
    }

    private fun handleVisibility(isVisible: Boolean) {
        binding.scrollContainer.isVisible = isVisible
        binding.noDataContainer.isVisible = !isVisible
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
