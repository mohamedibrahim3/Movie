package com.mada.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "Banner Screen" ) {

        composable("Banner Screen") {
            BannerScreen(navController = navController)

        }
        composable("Home Screen") {
            HomeScreen(navController = navController)

        }
    }
}