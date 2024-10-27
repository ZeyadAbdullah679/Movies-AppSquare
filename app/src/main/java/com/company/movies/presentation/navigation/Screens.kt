package com.company.movies.presentation.navigation

enum class Screens(val route: String) {
    Onboarding("onboarding"),
    Home("home"),
    MovieDetails("movieDetails/{movieId}/{language}");

    companion object {
        fun getMovieDetailsRoute(movieId: Int, language: String): String {
            return "movieDetails/$movieId/$language"
        }
    }
}
