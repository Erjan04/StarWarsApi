package com.example.starwarsapi.domain.people.usecases

import com.example.starwarsapi.domain.people.repository.PeopleRepository
import javax.inject.Inject

class GetPeopleDetailUseCase @Inject constructor(private val repository: PeopleRepository) {

    operator fun invoke(id: Int) = repository.getPeopleDetailData(id)

}