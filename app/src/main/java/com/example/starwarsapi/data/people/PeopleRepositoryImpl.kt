package com.example.starwarsapi.data.people

import com.example.starwarsapi.data.people.remote.PeopleApi
import com.example.starwarsapi.domain.people.repository.PeopleRepository
import com.example.starwarsapi.domain.people.entity.PeopleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(private val peopleApi: PeopleApi) :
    PeopleRepository {

    override suspend fun getPeopleList(): Flow<List<PeopleEntity>> {
        return flow {
            val response = peopleApi.getPeopleResponse()
            if (response.isSuccessful) {
                val body = response.body()
                body?.results?.let { emit(it) }
            }
        }
    }

}