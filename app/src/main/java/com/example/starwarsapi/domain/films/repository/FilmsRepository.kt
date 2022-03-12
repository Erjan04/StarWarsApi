package com.example.starwarsapi.domain.films.repository

import com.example.starwarsapi.domain.films.entity.FilmsEntity
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {

    suspend fun getFilmsList(): Flow<List<FilmsEntity>>

}