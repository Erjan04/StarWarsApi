package com.example.starwarsapi.data.common.response

data class StarWarsResponse<D>(
    val count: Int,
    val next: String,
    val previous: String,
    val results: MutableList<D>
)