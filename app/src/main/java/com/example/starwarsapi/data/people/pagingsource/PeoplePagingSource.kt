package com.example.starwarsapi.data.people.pagingsource

import com.example.starwarsapi.data.people.dto.PeopleModelDto
import com.example.starwarsapi.data.people.dto.toPeople
import com.example.starwarsapi.data.people.remote.PeopleApi
import com.example.starwarsapi.domain.common.base.BasePagingSource
import com.example.starwarsapi.domain.people.entity.PeopleEntity

class PeoplePagingSource(
    private val api: PeopleApi
) : BasePagingSource<PeopleModelDto, PeopleEntity>
    ({ api.getPeopleResponse(it) },
    { data -> data.map { it.toPeople() } }) {
}
