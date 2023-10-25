package com.husqvarna.popularmovies.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.husqvarna.popularmovies.R
import com.husqvarna.popularmovies.databinding.ActivityMainBinding
import com.husqvarna.popularmovies.ui.viewmodel.SharedViewModel
import com.husqvarna.popularmovies.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Main activity for the app. Represent the single activity architecture.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    @Inject
    lateinit var mAppContext: Context
    private val sharedViewModel: SharedViewModel by viewModels()
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkRequest: NetworkRequest
    private lateinit var networkCallback: NetworkCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        observeData(activityMainBinding)
        initConnectivityManager()
    }

    override fun onResume() {
        super.onResume()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    /**
     * Monitor connectivity status.
     */
    private fun initConnectivityManager() {
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()

        networkCallback = object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                lifecycleScope.launch(Dispatchers.Main) {
                    if (navController.currentDestination?.id == R.id.connectivityStatusFragment) {
                        navController.popBackStack()
                    }
                }
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                showToast(getString(R.string.slow_internet))
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                lifecycleScope.launch(Dispatchers.Main) {
                    navController.navigate(R.id.connectivityStatusFragment)
                }
            }
        }
    }

    /**
     * Observe data.
     */
    private fun observeData(activityMainBinding: ActivityMainBinding) {
        sharedViewModel.loaderLiveData.observe(this) {
            activityMainBinding.loader.isVisible = it
        }
    }
}
