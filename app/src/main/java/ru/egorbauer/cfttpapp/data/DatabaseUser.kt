package ru.egorbauer.cfttpapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.egorbauer.cfttpapp.domain.entity.User

@Entity(
    tableName = "usersTable"
)
data class DatabaseUser(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "fio") val fio: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "dob") val dob: String,
    @ColumnInfo(name = "age") val age: String,
    @ColumnInfo(name = "nat") val nat: String,
)

fun DatabaseUser.toUser() = User(
    id = this.id,
    gender = this.gender,
    name = this.fio,
    location = this.address,
    email = this.email,
    dob = this.dob,
    age = this.age,
    phone = this.phone,
    picture = this.photo,
    nat = this.nat
)