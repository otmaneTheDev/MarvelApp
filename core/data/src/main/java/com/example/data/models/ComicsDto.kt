package com.example.data.models


import com.google.gson.annotations.SerializedName

data class ComicsDto(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<SummaryDto>,
    @SerializedName("returned")
    val returned: Int
)