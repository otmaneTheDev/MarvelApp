package com.example.data.models


import com.google.gson.annotations.SerializedName

data class CharacterDataWrapperResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val characterDataContainer: CharacterDataContainerDto,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("status")
    val status: String
)