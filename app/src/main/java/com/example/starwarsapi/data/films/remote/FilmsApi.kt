package com.example.starwarsapi.data.films.remote

import com.example.starwarsapi.domain.films.entity.FilmsResponseEntity
import retrofit2.Response
import retrofit2.http.GET

interface FilmsApi {

    @GET("films/")
    suspend fun getFilmsResponse(): Response<FilmsResponseEntity>

}