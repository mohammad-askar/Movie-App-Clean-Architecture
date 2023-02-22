package com.example.movieapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.feature_tv_show.presentation.home.HomeScreen
import com.example.movieapp.feature_tv_show.presentation.home.HomeViewModel
import com.example.movieapp.ui.theme.TvManiaTheme
import com.example.movieapp.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navController = rememberNavController()
            TvManiaTheme() {
                Surface() {
                    NavHost(navController = navController,
                        startDestination =Screen.HomeScreen.route,
                        ){
                        composable(route = Screen.HomeScreen.route){
                            val homeViewModel = hiltViewModel<HomeViewModel>()
                            HomeScreen(viewModel = homeViewModel, onTvShowClick = {

                            })
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TvManiaTheme() {
        Greeting("Android")
    }
}