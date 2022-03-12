package com.example.starwarsapi.data.starships.remote

import com.example.starwarsapi.domain.starships.entity.StarShipsResponseEntity
import retrofit2.Response
import retrofit2.http.GET

interface StarShipsApi {

    @GET("starships/")
    suspend fun getStarShipsResponse(): Response<StarShipsResponseEntity>

}