package com.example.movizephyr.endpoints.movies

import com.example.movizephyr.modal.movies.upcoming.UpComing
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UpcomingMovies {

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(@Header("Authorization") authorization: String):Response<UpComing>


}