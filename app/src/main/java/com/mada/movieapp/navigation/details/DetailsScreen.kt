package com.mada.movieapp.navigation.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mada.movieapp.domain.data.Details
import com.mada.movieapp.viewModel.MoviesViewModel

@Composable
fun DetailsScreen(id: Int) {
    val movieViewModel: MoviesViewModel = viewModel()
    var hasFetchedDetails by remember { mutableStateOf(false) }

    // Fetch details only once when the screen is first launched
    LaunchedEffect(id) {
        if (!hasFetchedDetails) {
            movieViewModel.id = id  // Set the id in the ViewModel
            movieViewModel.getDetailsById(id)  // Fetch movie details based on id
            hasFetchedDetails = true
        }
    }

    val state = movieViewModel.state
    val details = state.detailsData

    if (state.error != null) {
        ErrorMessage(state.error)  // Display error if any
        return
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        BackGroundPoster(details = details)
        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState()), // Add vertical scroll
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(570.dp)) // Space for the poster
            DetailsContent(details = details)
        }
    }
}

@Composable
fun DetailsContent(details: Details) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.Bottom, // Align content to the bottom
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = details.title,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 38.sp,
            color = Color.White,
            lineHeight = 40.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Rating(details = details, modifier = Modifier.padding(vertical = 5.dp))
        Spacer(modifier = Modifier.height(10.dp))
        TextBuilder(icon = Icons.Filled.Person, title = "Summery:", bodyText = details.plot)
        Spacer(modifier = Modifier.height(10.dp))
        TextBuilder(icon = Icons.Filled.Person, title = "Actors:", bodyText = details.actors)
        Spacer(modifier = Modifier.height(15.dp))
        ImageRow(details = details)
    }
}

@Composable
fun ErrorMessage(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error: $error",
            color = Color.Red,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoadingMessage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Loading...",
            color = Color.Gray,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}
