package com.husqvarna.popularmovies.ui.fragments.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private var _splashFragmentBinding: FragmentSplashBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _splashFragmentBinding = FragmentSplashBinding.inflate(inflater, container, false)
        return _splashFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            delay(SPLASH_DELAY)
            findNavController().let {
                it.popBackStack()
                it.navigate(R.id.moviesFragment)
            }
        }
    }

    override fun onDestroyView() {
        _splashFragmentBinding = null
        super.onDestroyView()
    }

    companion object {
        private const val SPLASH_DELAY = 3000L
    }
}
