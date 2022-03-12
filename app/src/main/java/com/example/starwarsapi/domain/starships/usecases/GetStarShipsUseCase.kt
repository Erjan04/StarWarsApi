package com.example.starwarsapi.domain.starships.usecases

import com.example.starwarsapi.domain.starships.entity.StarShipsEntity
import com.example.starwarsapi.domain.starships.repository.StarShipsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStarShipsUseCase @Inject constructor(private val repository: StarShipsRepository){

    suspend fun invoke(): Flow<List<StarShipsEntity>> = repository.getStarShipsList()

}