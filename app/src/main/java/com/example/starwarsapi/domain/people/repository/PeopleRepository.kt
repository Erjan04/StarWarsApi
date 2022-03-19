package com.example.starwarsapi.domain.people.repository

import androidx.paging.PagingData
import com.example.starwarsapi.domain.people.entity.PeopleEntity
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {

    fun getPeopleList(): Flow<PagingData<PeopleEntity>>

}