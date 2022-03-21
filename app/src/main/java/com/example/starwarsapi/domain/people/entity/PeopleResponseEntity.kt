package com.example.starwarsapi.domain.people.entity

import com.example.starwarsapi.domain.common.base.BaseId
import com.google.gson.annotations.SerializedName

data class PeopleResponseEntity(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PeopleEntity>
)
data class PeopleEntity(
    val name: String,
    val height: String,
    val mass: String,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("skin_color")
    val skinColor: String,
    @SerializedName("eye_color")
    val eyeColor: String,
    val gender: String,
    val films: List<String>, override val id: Int,
): BaseId
