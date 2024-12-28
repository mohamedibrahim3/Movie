package com.mada.movieapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mada.movieapp.repository.Repository
import com.mada.movieapp.domain.data.ScreenState
import kotlinx.coroutines.launch

class MoviesViewModel:ViewModel() {

    private val repository = Repository()
    var state by mutableStateOf(ScreenState())

    init {

        viewModelScope.launch {
            val response = repository.getMovieList(state.page)
            state = state.copy(
                movies = response.body()!!.data
            )
        }
    }
}