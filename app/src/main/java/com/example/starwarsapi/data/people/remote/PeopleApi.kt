package com.example.starwarsapi.data.people.remote

import com.example.starwarsapi.data.common.response.StarWarsResponse
import com.example.starwarsapi.data.people.dto.PeopleModelDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleApi {

    @GET("/api/people")
    suspend fun getPeopleResponse(
        @Query("page") page: Int
    ): StarWarsResponse<PeopleModelDto>

}