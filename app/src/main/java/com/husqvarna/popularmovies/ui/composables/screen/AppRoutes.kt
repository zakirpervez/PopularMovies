package com.husqvarna.popularmovies.ui.composables.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.husqvarna.popularmovies.ui.composables.screen.movies.MoviesScreen
import com.husqvarna.popularmovies.ui.viewmodel.MoviesViewModel

@Composable
fun AppRoutes() {
    val navController = rememberNavController()
    val moviesViewModel = hiltViewModel<MoviesViewModel>()
    NavHost(navController = navController, startDestination = "splash_screen", builder = {
        composable(route = "splash_screen"){
            SplashScreen {
                navController.popBackStack(route = "splash_screen", inclusive = true)
                navController.navigate("movies_screen")
            }
        }
        composable(route = "movies_screen") {
            MoviesScreen(moviesViewModel = moviesViewModel) {
                navController.navigate("movie_details_screen/${it}")
            }
        }
        composable(route = "movie_details_screen/{movie_id}", arguments = listOf(
            navArgument(name = "movie_id") {
                type = NavType.IntType
            }
        )) {
            MovieDetailsScreen(movieId = it.arguments?.getInt("movie_id") ?: 0)
        }
    })
}
