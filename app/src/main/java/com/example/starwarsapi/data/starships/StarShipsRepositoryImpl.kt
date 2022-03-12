package com.example.starwarsapi.data.starships

import com.example.starwarsapi.data.starships.remote.StarShipsApi
import com.example.starwarsapi.domain.starships.entity.StarShipsEntity
import com.example.starwarsapi.domain.starships.repository.StarShipsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StarShipsRepositoryImpl @Inject constructor(private val starShipsApi: StarShipsApi) :
    StarShipsRepository {
    override suspend fun getStarShipsList(): Flow<List<StarShipsEntity>> {
        return flow {
            val response = starShipsApi.getStarShipsResponse()
            if (response.isSuccessful) {
                val body = response.body()
                body?.results?.let { emit(it) }
            }
        }
    }
}