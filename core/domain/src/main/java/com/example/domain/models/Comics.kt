package com.example.domain.models

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Summary>,
    val returned: Int
)