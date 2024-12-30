package com.mada.movieapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mada.movieapp.repository.Repository
import com.mada.movieapp.domain.data.ScreenState
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: Repository = Repository() // Dependency injection with default value
) : ViewModel() {

    var state by mutableStateOf(ScreenState())
    var id by mutableIntStateOf(0)

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val response = repository.getMovieList(state.page)
                if (response.isSuccessful) {
                    state = state.copy(
                        movies = response.body()?.data.orEmpty() // Safe handling of nulls
                    )
                } else {
                    // Handle API failure
                    state = state.copy(
                        error = "Failed to load movies: ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                // General exception handling
                state = state.copy(
                    error = e.localizedMessage ?: "Unknown error occurred"
                )
            }
        }
    }

    fun getDetailsById() {
        viewModelScope.launch {
            try {
                val response = repository.getDetailsById(id)
                if (response.isSuccessful) {
                    state = response.body()?.let {
                        state.copy(
                            detailsData = it
                        )
                    }!!
                } else {
                    // Handle API failure
                    state = state.copy(
                        error = "Failed to load details: ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                state = state.copy(
                    error = e.localizedMessage ?: "Unknown error occurred"
                )
            }
        }
    }
}
