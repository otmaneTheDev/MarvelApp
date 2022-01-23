package com.example.domain.models

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Summary>,
    val returned: Int
)