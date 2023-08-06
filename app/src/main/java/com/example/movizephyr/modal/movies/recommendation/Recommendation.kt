package com.example.movizephyr.modal.movies.recommendation

data class Recommendation(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)