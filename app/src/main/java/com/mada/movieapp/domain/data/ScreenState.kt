package com.mada.movieapp.domain.data

data class ScreenState(
    val movies: List<Data> = emptyList(),
    val page: Int = 1
)
