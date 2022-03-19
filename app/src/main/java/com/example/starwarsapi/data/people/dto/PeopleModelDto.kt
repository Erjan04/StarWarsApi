package com.example.starwarsapi.data.people.dto

import com.example.starwarsapi.domain.people.entity.PeopleEntity
import com.google.gson.annotations.SerializedName

class PeopleModelDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("mass")
    val mass: String,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("skin_color")
    val skinColor: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    @SerializedName("films")
    val films: List<String>,
    @SerializedName("id")
    val id: Int
)

fun PeopleModelDto.toPeople(): PeopleEntity {
    return PeopleEntity(
        name,
        height,
        mass,
        hairColor,
        skinColor,
        eyeColor,
        films,
        id
    )
}