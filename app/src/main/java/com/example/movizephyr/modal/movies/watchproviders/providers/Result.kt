package com.example.movizephyr.modal.movies.watchproviders.providers

data class Result(
    val display_priorities: DisplayPriorities,
    val display_priority: Int,
    val logo_path: String,
    val provider_id: Int,
    val provider_name: String
)