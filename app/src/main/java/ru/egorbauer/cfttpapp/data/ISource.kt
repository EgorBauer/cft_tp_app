package ru.egorbauer.cfttpapp.data

import retrofit2.Response
import retrofit2.http.GET

interface ISource{
    @GET("api/?results=25")
    suspend fun getUsers(): Response<Result>
}

fun provideSource(network: INetwork): ISource =
    network.retrofit.create(
        ISource::class.java
    )