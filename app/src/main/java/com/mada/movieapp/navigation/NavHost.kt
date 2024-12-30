package com.mada.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mada.movieapp.navigation.banner.BannerScreen
import com.mada.movieapp.navigation.details.DetailsScreen
import com.mada.movieapp.navigation.home.HomeScreen
import com.mada.movieapp.viewModel.MoviesViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.BannerScreen.route
    ) {
        composable(route = Route.BannerScreen.route) {
            BannerScreen(navController = navController)
        }

        composable(route = Route.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Route.DetailsScreen.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("id")
            movieId?.let { id ->
                DetailsScreen(id = id)
            }
        }
    }
}

sealed class Route(val route: String) {
    object BannerScreen : Route("Banner screen")
    object HomeScreen : Route("Home screen")
    object DetailsScreen : Route("Details screen/{id}")
}
