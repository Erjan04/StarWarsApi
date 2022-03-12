package com.example.starwarsapi.domain.films.usecases

import com.example.starwarsapi.domain.films.entity.FilmsEntity
import com.example.starwarsapi.domain.films.repository.FilmsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilmsListUseCase @Inject constructor(private val repository: FilmsRepository) {

    suspend fun invoke(): Flow<List<FilmsEntity>> = repository.getFilmsList()

}