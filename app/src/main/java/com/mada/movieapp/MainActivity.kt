package com.mada.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mada.movieapp.navigation.Navigation
import com.mada.movieapp.ui.theme.MovieAppTheme
import com.mada.movieapp.viewModel.MoviesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieApp()
        }
    }
}

@Composable
fun MovieApp() {
    MovieAppTheme {
        Navigation()
    }
}
