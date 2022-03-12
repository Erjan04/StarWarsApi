package com.example.starwarsapi.data.planets

import com.example.starwarsapi.data.common.module.NetworkModule
import com.example.starwarsapi.data.planets.remote.PlanetsApi
import com.example.starwarsapi.domain.planets.repository.PlanetsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class PlanetsModule {

    @Singleton
    @Provides
    fun providesPlanetsApi(retrofit: Retrofit): PlanetsApi {
        return retrofit.create(PlanetsApi::class.java)
    }

    @Singleton
    @Provides
    fun providePlanetsRepository(planetsApi: PlanetsApi): PlanetsRepository {
        return PlanetsRepositoryImpl(planetsApi)
    }

}