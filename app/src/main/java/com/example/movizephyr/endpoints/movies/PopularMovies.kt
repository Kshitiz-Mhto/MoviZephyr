package com.example.movizephyr.endpoints.movies

import com.example.movizephyr.modal.movies.popular.Popular
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface PopularMovies {

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(@Header("Authorization") auth: String): Response<Popular>
}