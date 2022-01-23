package com.example.domain.models

data class CharacterDataContainer(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val characters: List<Character>,
    val total: Int
)