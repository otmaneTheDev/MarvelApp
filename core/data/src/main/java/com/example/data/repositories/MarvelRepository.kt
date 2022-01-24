package com.example.data.repositories

import com.example.data.models.CharacterDataWrapperResponse
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    suspend fun getCharacters(offset: Int): Flow<CharacterDataWrapperResponse>
    suspend fun getCharactersById(characterId: Int): Flow<CharacterDataWrapperResponse>
}