package com.example.starwarsapi.data.people

import com.example.starwarsapi.data.people.pagingsource.PeoplePagingSource
import com.example.starwarsapi.data.people.remote.PeopleApi
import com.example.starwarsapi.domain.common.base.BaseRepository
import com.example.starwarsapi.domain.people.repository.PeopleRepository
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(private val peopleApi: PeopleApi) : BaseRepository(),
    PeopleRepository {

    override fun getPeopleList() = executionPaging(PeoplePagingSource(peopleApi))

}
