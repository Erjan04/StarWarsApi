package com.example.starwarsapi.domain.planets.usecases

import com.example.starwarsapi.domain.planets.entity.PlanetsEntity
import com.example.starwarsapi.domain.planets.repository.PlanetsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetsListUseCase @Inject constructor(private val repository: PlanetsRepository) {

    suspend fun invoke(): Flow<List<PlanetsEntity>> = repository.getPlanetsList()

}