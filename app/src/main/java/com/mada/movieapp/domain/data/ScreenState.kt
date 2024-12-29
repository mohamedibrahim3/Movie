package com.mada.movieapp.domain.data

data class ScreenState(
    val movies: List<Data> = emptyList(),
    val page: Int = 3,
    val detailsData: Details = Details(),
    val endReached: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false
)
