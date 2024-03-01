package ru.egorbauer.cfttpapp.data.datasource

import ru.egorbauer.cfttpapp.data.datasource.remote.entity.ApiUser

class Source(private val userApi: ISource) {
    suspend fun getUsers(): List<ApiUser> {
        val response = userApi.getUsers()
        if (response.isSuccessful) {
            response.body()?.let {
                return it.users
            }
        }
        throw RuntimeException("Error while connecting backend")
    }
}