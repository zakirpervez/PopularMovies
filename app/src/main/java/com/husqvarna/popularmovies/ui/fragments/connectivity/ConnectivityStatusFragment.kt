package com.husqvarna.popularmovies.ui.fragments.connectivity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.husqvarna.popularmovies.R
import timber.log.Timber

/**
 * Connectivity status screen.
 */
class ConnectivityStatusFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_connectivity_status, container, false)
    }
}
