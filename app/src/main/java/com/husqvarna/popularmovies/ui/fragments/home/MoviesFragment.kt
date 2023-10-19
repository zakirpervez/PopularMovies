package com.husqvarna.popularmovies.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.api.models.response.ResultsItem
import com.husqvarna.popularmovies.databinding.FragmentMoviesBinding
import com.husqvarna.popularmovies.ui.fragments.home.adapter.MoviesAdapter
import com.husqvarna.popularmovies.ui.viewmodel.MoviesViewModel
import com.husqvarna.popularmovies.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * [MoviesFragment] represent the movies list screen.
 */
@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _moviesBinding: FragmentMoviesBinding? = null
    private val moviesBinding by lazy { _moviesBinding!! }

    @Inject
    lateinit var moviesAdapter: MoviesAdapter
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesViewModel.fetchMovies(1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _moviesBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        moviesBinding.viewModel = moviesViewModel
        return moviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.showLoader(true)
        observeData()
        setupViews()
    }

    /**
     * Observe the data from the [MoviesViewModel].
     */
    private fun observeData() {
        moviesViewModel.moviesLiveData.observe(viewLifecycleOwner) {
            it?.apply { moviesAdapter.updateMovies(this) }
            sharedViewModel.showLoader(false)
        }

        moviesViewModel.errorLiveData.observe(viewLifecycleOwner) {
            sharedViewModel.showLoader(false)
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Setup the views.
     */
    private fun setupViews() {
        moviesAdapter.setOnMovieItemClickListener(object :
            MoviesAdapter.OnMovieItemClickListener {
            override fun onMovieClick(movie: ResultsItem) {
                navigateToDetails(movie)
            }
        })

        with(moviesBinding.smsRecyclerView) {
            itemAnimator = null
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = moviesAdapter
        }
    }

    /**
     * Navigate to the movie details screen.
     * @param movie The [ResultsItem] movie item.
     */
    private fun navigateToDetails(movie: ResultsItem) {
        movie.id?.let {
            // Added bundle due to bug present in android giraffe where it won't recognize the safe args inside navigation graph.
            val args = Bundle()
            args.putInt("movieId", it)
            findNavController().navigate(R.id.action_moviesFragment_to_movieDetailFragment, args)
        }
    }

    override fun onDestroy() {
        _moviesBinding = null
        super.onDestroy()
    }
}
