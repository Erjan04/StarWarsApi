package com.example.starwarsapi.domain.starships.repository

import com.example.starwarsapi.domain.starships.entity.StarShipsEntity
import kotlinx.coroutines.flow.Flow

interface StarShipsRepository {

    suspend fun getStarShipsList(): Flow<List<StarShipsEntity>>

}