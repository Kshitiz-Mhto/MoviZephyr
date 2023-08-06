package com.example.movizephyr.endpoints.movies

import com.example.movizephyr.modal.movies.recommendation.Recommendation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RecommendedMovies {

    @GET("/3/movie/{movie_id}/similar")
    suspend fun getRecommendedMovies(@Header("Authorization") auth: String, @Path("movie_id") id:String): Response<Recommendation>

}