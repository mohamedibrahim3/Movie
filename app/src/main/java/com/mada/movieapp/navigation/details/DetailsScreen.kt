package com.mada.movieapp.navigation.details


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mada.movieapp.viewModel.MoviesViewModel

@Composable
fun DetailsScreen(id: Int) {
    val movieViewModel = viewModel<MoviesViewModel>()
    movieViewModel.id = id
    movieViewModel.getDetailsById()
    val state = movieViewModel.state

    val details = state.detailsData

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        BackGroundPoster(details = details)
        ForegroundPoster(details = details)
        Column(
            Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 50.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = details.title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 38.sp,
                color = Color.White,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center
            )
            Rating(details = details, modifier = Modifier)
            TextBuilder(icon = Icons.Filled.Info, title = "Summery:", bodyText = details.plot)
            TextBuilder(icon = Icons.Filled.Person, title = "Actors:", bodyText = details.actors)
            ImageRow(details = details)
        }
    }
}