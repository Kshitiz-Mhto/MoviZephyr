package com.example.movizephyr.endpoints.movies.search

import com.example.movizephyr.modal.movies.search.byid.SearchMoviesId
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SearchByMovieId {

    @GET("/3/movie/{id}")
    suspend fun getSearchByMoviesId(@Header("Authorization") auth:String, @Path("id") movie_id:String): Response<SearchMoviesId>

}