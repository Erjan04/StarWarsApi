package com.example.starwarsapi.domain.films.entity

import com.example.starwarsapi.domain.common.base.BaseId
import com.google.gson.annotations.SerializedName

data class FilmsResponseEntity(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<FilmsEntity>
)

data class FilmsEntity(
    val title: String,
    @SerializedName("episode_id")
    val episodeId: Int,
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    val director: String,
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String, override val id: Int
) : BaseId
