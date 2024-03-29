package ru.egorbauer.cfttpapp.domain.entity

data class User(
    val id: Int,
    val gender: String,
    val name: String,
    val location: String,
    val email: String,
    val dob: String,
    val age: String,
    val phone: String,
    val picture: String,
    val nat: String
)
