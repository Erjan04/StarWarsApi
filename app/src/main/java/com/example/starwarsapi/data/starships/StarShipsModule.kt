package com.example.starwarsapi.data.starships

import com.example.starwarsapi.data.common.module.NetworkModule
import com.example.starwarsapi.data.starships.remote.StarShipsApi
import com.example.starwarsapi.domain.starships.repository.StarShipsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class StarShipsModule {

    @Singleton
    @Provides
    fun providesStarShipsApi(retrofit: Retrofit): StarShipsApi {
        return retrofit.create(StarShipsApi::class.java)
    }

    @Singleton
    @Provides
    fun providesStarShipsRepository(starShipsApi: StarShipsApi): StarShipsRepository {
        return StarShipsRepositoryImpl(starShipsApi)
    }

}