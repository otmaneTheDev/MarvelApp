package com.example.data.service

import com.example.data.models.CharacterDataWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int = 0
    ): CharacterDataWrapperResponse

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId: Int): CharacterDataWrapperResponse
}