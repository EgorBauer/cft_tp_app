package ru.egorbauer.cfttpapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.egorbauer.cfttpapp.domain.entity.User

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "results")
    val users:List<ApiUser>
)

@JsonClass(generateAdapter = true)
data class ApiUser(
    @Json(name = "gender")
    val gender:String,
    @Json(name = "name")
    val name:Name,
    @Json(name = "location")
    val location:Location,
    @Json(name = "email")
    val email:String,
    @Json(name = "dob")
    val dob:Dob,
    @Json(name = "phone")
    val phone:String,
    @Json(name = "picture")
    val picture:Picture,
    @Json(name = "nat")
    val nat:String
)

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "title")
    val title:String,
    @Json(name = "first")
    val first:String,
    @Json(name = "last")
    val last:String
)

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "street")
    val street:Street,
    @Json(name = "city")
    val city:String,
    @Json(name = "state")
    val state:String,
    @Json(name = "country")
    val country:String,
    @Json(name = "coordinates")
    val coordinates:Coordinates,
)

@JsonClass(generateAdapter = true)
data class Coordinates(
    @Json(name = "latitude")
    val latitude:String,
    @Json(name = "longitude")
    val longitude:String
)

@JsonClass(generateAdapter = true)
data class Street(
    @Json(name = "number")
    val number:Int,
    @Json(name = "name")
    val name:String
)

@JsonClass(generateAdapter = true)
data class Dob(
    @Json(name = "date")
    val date:String,
    @Json(name = "age")
    val age:Int
)

@JsonClass(generateAdapter = true)
data class Picture(
    @Json(name = "large")
    val large:String,
    @Json(name = "medium")
    val medium:String,
    @Json(name = "thumbnail")
    val thumbnail:String
)

fun ApiUser.toUsers() = User(
    gender = this.gender,
    name = "${this.name.title} ${this.name.first} ${this.name.last}",
    location = "${this.location.country}, ${this.location.state}, ${this.location.city}" +
            "${this.location.street.name}, ${this.location.street.number} " +
            "${this.location.coordinates.longitude}, ${this.location.coordinates.latitude}",
    email = this.email,
    dob = this.dob.date,
    age = this.dob.age.toString(),
    phone = this.phone,
    picture = this.picture.large,
    nat = this.nat,
    id = this.toDB().id
)

fun ApiUser.toDB() = DatabaseUser(
    photo = this.picture.large,
    fio = "${this.name.title} ${this.name.first} ${this.name.last}",
    phone = this.phone,
    address = "${this.location.country}, ${this.location.state}, ${this.location.city}" +
            "${this.location.street.name}, ${this.location.street.number}," +
            "${this.location.coordinates.longitude}, ${this.location.coordinates.latitude}",
    email = this.email,
    dob = this.dob.date,
    gender = this.gender,
    age = this.dob.age.toString(),
    nat = this.nat
)