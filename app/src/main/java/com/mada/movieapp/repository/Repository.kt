package com.mada.movieapp.repository

import com.mada.movieapp.domain.data.Details
import com.mada.movieapp.domain.data.MoviesList
import com.mada.movieapp.utils.RetrofitInstance
import retrofit2.Response

@Suppress("UNREACHABLE_CODE")
class Repository {

    suspend fun getMovieList(page: Int): Response<MoviesList> {
        return try {
            RetrofitInstance.api.getMovies(page)
        } catch (e: Exception) {
            // Handle the error gracefully (log, etc.)
            Response.error(500, okhttp3.ResponseBody.create(null, ""))
        }
    }
        suspend fun getDetailsById(id: Int): Response<Details> {
            return try {
                RetrofitInstance.api.getDetailsById(id = id)
            } catch (e: Exception) {
                // Handle error gracefully
                Response.error(500, okhttp3.ResponseBody.create(null, ""))
            }
        }
}