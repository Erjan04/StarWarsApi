package com.example.starwarsapi.data.films

import com.example.starwarsapi.data.films.remote.FilmsApi
import com.example.starwarsapi.domain.films.entity.FilmsEntity
import com.example.starwarsapi.domain.films.repository.FilmsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(private val filmsApi: FilmsApi) : FilmsRepository {

    override suspend fun getFilmsList(): Flow<List<FilmsEntity>> {
        return flow {
            val response = filmsApi.getFilmsResponse()
            if (response.isSuccessful) {
                val body = response.body()
                body?.results?.let { emit(it) }
            }
        }
    }


}