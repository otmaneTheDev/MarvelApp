package com.example.data.models


import com.google.gson.annotations.SerializedName

data class CharacterDataContainerDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val characters: List<CharacterDto>,
    @SerializedName("total")
    val total: Int
)