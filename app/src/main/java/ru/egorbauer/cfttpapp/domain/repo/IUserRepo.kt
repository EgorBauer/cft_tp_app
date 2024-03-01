package ru.egorbauer.cfttpapp.domain.repo

import ru.egorbauer.cfttpapp.data.DatabaseUser
import ru.egorbauer.cfttpapp.domain.entity.User


interface IUserRepo {
    suspend fun getUser(): List<User>
    suspend fun getUserFromDatabase(): List<User>
    suspend fun insertNewUser(user: DatabaseUser)
    suspend fun updateList(): List<User>
}