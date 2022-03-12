package com.example.starwarsapi.data.people

import com.example.starwarsapi.data.common.module.NetworkModule
import com.example.starwarsapi.data.people.remote.PeopleApi
import com.example.starwarsapi.domain.people.repository.PeopleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class PeopleModule {

    @Singleton
    @Provides
    fun providesPeopleApi(retrofit: Retrofit): PeopleApi {
        return retrofit.create(PeopleApi::class.java)
    }

    @Singleton
    @Provides
    fun providePeopleRepository(peopleApi: PeopleApi): PeopleRepository {
        return PeopleRepositoryImpl(peopleApi)
    }

}