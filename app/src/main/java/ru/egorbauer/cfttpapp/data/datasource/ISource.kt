package ru.egorbauer.cfttpapp.data.datasource

import retrofit2.Response
import retrofit2.http.GET
import ru.egorbauer.cfttpapp.data.datasource.remote.network.INetwork
import ru.egorbauer.cfttpapp.data.datasource.remote.entity.Result

interface ISource {
    @GET("api/?results=15")
    suspend fun getUsers(): Response<Result>
}

fun provideSource(network: INetwork): ISource =
    network.retrofit.create(
        ISource::class.java
    )