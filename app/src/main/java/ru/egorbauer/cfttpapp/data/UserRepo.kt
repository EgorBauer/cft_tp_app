package ru.egorbauer.cfttpapp.data

import ru.egorbauer.cfttpapp.domain.IUserRepo
import ru.egorbauer.cfttpapp.domain.entity.User

class UserRepo(
    private val source: Source,
    private val db: CftDatabase
) : IUserRepo {
    override suspend fun getUser(): List<User> {
        val data = getUserFromDatabase()
        return data.ifEmpty {
            val result = source.getUsers()
            return result.map {
                db.getUserDao().addNewUser(it.toDB())
                it.toUsers()
            }
        }
    }

    override suspend fun getUserFromDatabase(): List<User> {
        return db.getUserDao().getUserFromDatabase().map {
            it.toUser()
        }
    }

    override suspend fun insertNewUser(user: DatabaseUser) {
        db.getUserDao().addNewUser(user)
    }

    override suspend fun updateList(): List<User> {
        db.getUserDao().deleteUsersFromTable()
        return getUser()
    }
}


