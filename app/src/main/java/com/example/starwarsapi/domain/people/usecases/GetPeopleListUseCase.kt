package com.example.starwarsapi.domain.people.usecases

import com.example.starwarsapi.domain.people.repository.PeopleRepository
import com.example.starwarsapi.domain.people.entity.PeopleEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleListUseCase @Inject constructor(private val peopleRepository: PeopleRepository) {

    suspend fun invoke(): Flow<List<PeopleEntity>> = peopleRepository.getPeopleList()

}