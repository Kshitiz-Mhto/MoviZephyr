package com.example.movizephyr.endpoints.movies

import com.example.movizephyr.modal.movies.credits.Credits
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MoviesCredits {

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getMoviesCredits(@Header("Authorization") authorization: String, @Path("movie_id") id: String): Response<Credits>


}