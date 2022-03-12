package com.example.starwarsapi.data.people.remote

import com.example.starwarsapi.domain.people.entity.PeopleResponseEntity
import retrofit2.Response
import retrofit2.http.GET

interface PeopleApi {

    @GET("people/")
    suspend fun getPeopleResponse(): Response<PeopleResponseEntity>

}