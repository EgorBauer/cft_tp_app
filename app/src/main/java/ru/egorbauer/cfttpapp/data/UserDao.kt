package ru.egorbauer.cfttpapp.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserDao {
    @Query("Select * from usersTable")
    suspend fun getUserFromDatabase():List<DatabaseUser>

    @Upsert
    suspend fun addNewUser(user: DatabaseUser)

    @Query("Delete from usersTable")
    suspend fun deleteUsersFromTable()
}