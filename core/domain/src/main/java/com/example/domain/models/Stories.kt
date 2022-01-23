package com.example.domain.models

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<Summary>,
    val returned: Int
)