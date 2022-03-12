package com.example.starwarsapi.data.planets

import com.example.starwarsapi.data.planets.remote.PlanetsApi
import com.example.starwarsapi.domain.planets.entity.PlanetsEntity
import com.example.starwarsapi.domain.planets.repository.PlanetsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetsRepositoryImpl @Inject constructor(private val api: PlanetsApi) : PlanetsRepository {

    override suspend fun getPlanetsList(): Flow<List<PlanetsEntity>> {
        return flow {
            val response = api.getPlanetsResponse()
            if (response.isSuccessful) {
                val body = response.body()
                body?.results?.let { emit(it) }
            }
        }
    }

}