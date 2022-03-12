package com.example.starwarsapi.data.planets.remote

import com.example.starwarsapi.domain.planets.entity.PlanetsResponseEntity
import retrofit2.Response
import retrofit2.http.GET

interface PlanetsApi {

    @GET("planets/")
    suspend fun getPlanetsResponse(): Response<PlanetsResponseEntity>
}