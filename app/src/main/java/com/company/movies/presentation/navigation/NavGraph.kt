package com.company.movies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.company.movies.presentation.components.connectionStates.LossConnection
import com.company.movies.presentation.screens.home.HomeScreen
import com.company.movies.presentation.screens.movieDetails.MovieDetailsScreen
import com.company.movies.presentation.screens.onboarding.OnboardingScreen
import com.company.movies.util.ConnectivityObserver

@Composable
fun NavGraph(
    isNetworkAvailable: ConnectivityObserver.Status,
    modifier: Modifier
) {
    val navController = rememberNavController()

    if (isNetworkAvailable == ConnectivityObserver.Status.Unavailable ||
        isNetworkAvailable == ConnectivityObserver.Status.Lost
    ) {
        LossConnection()
        return
    }

    NavHost(
        navController = navController,
        startDestination = Screens.Onboarding.name,
    ) {
        composable(route = Screens.Onboarding.name) {
            OnboardingScreen(onEnterClicked = { navController.navigate(Screens.Home.name) })
        }

        composable(route = Screens.Home.route) {
            HomeScreen(
                onMovieClicked = { movieId, language ->
                    navController.navigate(Screens.getMovieDetailsRoute(movieId, language))
                }
            )
        }

        composable(
            route = Screens.MovieDetails.route,
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType },
                navArgument("language") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            val language = backStackEntry.arguments?.getString("language") ?: ""

            MovieDetailsScreen(
                movieId = movieId,
                language = language,
                onBackClicked = { navController.popBackStack() },
                onMovieClicked = { id, lang ->
                    navController.navigate(
                        Screens.getMovieDetailsRoute(
                            id,
                            lang
                        )
                    )
                }
            )
        }
    }
}