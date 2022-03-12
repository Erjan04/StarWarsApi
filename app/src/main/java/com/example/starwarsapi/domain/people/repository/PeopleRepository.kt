package com.example.starwarsapi.domain.people.repository

import com.example.starwarsapi.domain.people.entity.PeopleEntity
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {

    suspend fun getPeopleList(): Flow<List<PeopleEntity>>

}