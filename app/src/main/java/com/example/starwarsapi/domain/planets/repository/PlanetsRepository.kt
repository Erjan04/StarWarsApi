package com.example.starwarsapi.domain.planets.repository

import com.example.starwarsapi.domain.planets.entity.PlanetsEntity
import kotlinx.coroutines.flow.Flow

interface PlanetsRepository {

    suspend fun getPlanetsList(): Flow<List<PlanetsEntity>>

}