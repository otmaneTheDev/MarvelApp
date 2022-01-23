package com.example.data.models


import com.google.gson.annotations.SerializedName

data class SummaryDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("type")
    val type: String?
)