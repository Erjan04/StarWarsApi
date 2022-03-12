package com.example.starwarsapi.domain.starships.entity

import com.example.starwarsapi.domain.common.base.BaseId

data class StarShipsResponseEntity(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<StarShipsEntity>
)

data class StarShipsEntity(
    val name: String, override val id: Int
) : BaseId