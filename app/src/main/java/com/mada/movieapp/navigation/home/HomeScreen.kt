package com.mada.movieapp.navigation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mada.movieapp.navigation.home.item.ItemUi
import com.mada.movieapp.navigation.home.item.TopBar
import com.mada.movieapp.viewModel.MoviesViewModel

@Composable
fun HomeScreen(navController: NavController){
    val movieViewModel = viewModel<MoviesViewModel>()
    val state = movieViewModel.state
    Scaffold(
        modifier = Modifier.background(Color.Transparent),
        topBar = {
            TopBar()
        }, content = { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(
                        Color.Transparent
                    ),
                content = {
                    items(state.movies.size) {
                        ItemUi(
                        itemIndex = it, movieList = state.movies,
                        navController = navController
                    )
                    }

                }
                    )

                }
            )

}
