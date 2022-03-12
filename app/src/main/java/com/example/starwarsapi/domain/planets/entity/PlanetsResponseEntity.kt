package com.example.starwarsapi.domain.planets.entity

import com.example.starwarsapi.domain.common.base.BaseId

data class PlanetsResponseEntity(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PlanetsEntity>
)

data class PlanetsEntity(
    val name: String,
    override val id: Int
) : BaseId