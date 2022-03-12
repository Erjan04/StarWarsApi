package com.example.starwarsapi.data.films

import com.example.starwarsapi.data.common.module.NetworkModule
import com.example.starwarsapi.data.films.remote.FilmsApi
import com.example.starwarsapi.domain.films.repository.FilmsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class FilmsModule {

    @Singleton
    @Provides
    fun providesFilmsApi(retrofit: Retrofit): FilmsApi {
        return retrofit.create(FilmsApi::class.java)
    }

    @Singleton
    @Provides
    fun providesFilmsRepository(filmsApi: FilmsApi): FilmsRepository {
        return FilmsRepositoryImpl(filmsApi)
    }

}