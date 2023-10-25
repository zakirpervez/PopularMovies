package com.husqvarna.popularmovies.ui.fragments.connectivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.husqvarna.popularmovies.databinding.FragmentConnectivityStatusBinding

/**
 * Connectivity status screen.
 */
class ConnectivityStatusFragment : Fragment() {
    private var _binding: FragmentConnectivityStatusBinding? = null
    private val binding by lazy { _binding!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentConnectivityStatusBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
