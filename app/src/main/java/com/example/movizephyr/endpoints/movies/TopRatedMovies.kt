package com.example.movizephyr.endpoints.movies

import com.example.movizephyr.modal.movies.toprated.TopRated
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TopRatedMovies {
    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(@Header("Authorization") auth: String): Response<TopRated>
}