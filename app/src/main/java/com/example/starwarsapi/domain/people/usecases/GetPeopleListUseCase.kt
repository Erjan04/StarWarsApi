package com.example.starwarsapi.domain.people.usecases

import com.example.starwarsapi.domain.people.repository.PeopleRepository
import javax.inject.Inject

class GetPeopleListUseCase
@Inject constructor(private val peopleRepository: PeopleRepository) {

    operator fun invoke() = peopleRepository.getPeopleList()

}