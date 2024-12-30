package com.mada.movieapp.navigation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mada.movieapp.navigation.home.item.ItemUi
import com.mada.movieapp.navigation.home.item.TopBar
import com.mada.movieapp.viewModel.MoviesViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val movieViewModel = viewModel<MoviesViewModel>()
    val state = movieViewModel.state
    val gridState = rememberLazyGridState() // Use LazyGridState instead of LazyListState

    // Listen for the end of the list and trigger loading more movies
    if (!state.isLoading && !state.endReached) {
        gridState.OnBottomReached {
            movieViewModel.fetchMovies() // Load more movies
        }
    }

    Scaffold(
        modifier = Modifier.background(Color.Transparent),
        topBar = {
            TopBar() // Display the top bar
        },
        content = { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Set the number of columns in the grid
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color.Transparent),
                state = gridState, // Use LazyGridState here
                content = {
                    items(state.movies) { movie ->
                        ItemUi(
                            itemIndex = state.movies.indexOf(movie),
                            movieList = state.movies,
                            navController = navController
                        )
                    }
                }
            )
        }
    )
}

// Extension function to detect when the user reaches the bottom of the list
fun LazyGridState.OnBottomReached(loadMore: () -> Unit) {
    val layoutInfo = this.layoutInfo
    if (layoutInfo.visibleItemsInfo.isNotEmpty()) {
        val lastVisibleItem = layoutInfo.visibleItemsInfo.last()
        if (lastVisibleItem.index == layoutInfo.totalItemsCount - 1) {
            loadMore() // Trigger loading more items when the last item is visible
        }
    }
}



