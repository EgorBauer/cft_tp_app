package ru.egorbauer.cfttpapp.data.datasource.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ru.egorbauer.cfttpapp.data.DatabaseUser

@Dao
interface UserDao {
    @Query("Select * from usersTable")
    suspend fun getUserFromDatabase(): List<DatabaseUser>

    @Upsert
    suspend fun addNewUser(user: DatabaseUser)

    @Query("Delete from usersTable")
    suspend fun deleteUsersFromTable()
}