package com.mada.movieapp.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mada.movieapp.repository.Repository
import com.mada.movieapp.domain.data.ScreenState
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: Repository = Repository() // Dependency injection with default value
) : ViewModel() {

    var state by mutableStateOf(ScreenState()) // Hold the UI state
    private var currentPage = 1 // Track the current page
    var id by mutableIntStateOf(0)

    init {
        fetchMovies() // Fetch initial set of movies
    }

    // Function to fetch movies from the repository
    fun fetchMovies() {
        // Prevent fetching if data is already loading or if all data has been loaded
        if (state.isLoading || state.endReached) return

        viewModelScope.launch {
            try {
                // Mark the loading state
                state = state.copy(isLoading = true)

                // Fetch data from repository
                val response = repository.getMovieList(currentPage)

                if (response.isSuccessful) {
                    // Get the list of movies from the response
                    val movies = response.body()?.data.orEmpty()

                    // Update the state with new movies by appending them to the existing list
                    state = state.copy(
                        movies = state.movies + movies, // Concatenate new movies with old
                        page = currentPage, // Set the current page
                        isLoading = false, // Set loading state to false after fetching
                        endReached = movies.isEmpty() // If no movies returned, mark as end reached
                    )

                    // Increment currentPage for the next fetch
                    currentPage++
                } else {
                    // Handle API failure
                    state = state.copy(
                        error = "Failed to load movies: ${response.message()}",
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                // General exception handling
                state = state.copy(
                    error = e.localizedMessage ?: "Unknown error occurred",
                    isLoading = false
                )
            }
        }
    }

    // Function to fetch movie details by ID
    fun getDetailsById(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getDetailsById(id)
                if (response.isSuccessful) {
                    state = response.body()?.let {
                        state.copy(
                            detailsData = it // Update details data in state
                        )
                    }!!
                } else {
                    // Handle API failure
                    state = state.copy(
                        error = "Failed to load details: ${response.message()}"
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
}
