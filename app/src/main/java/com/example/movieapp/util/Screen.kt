package com.example.movieapp.util

sealed class Screen(val route: String){
    object HomeScreen: Screen("home")
    object TvShowDetails: Screen("tvShow/{tvShowId}")
}