package com.example.data.repositories

import com.example.data.models.CharacterDataWrapperResponse
import com.example.data.service.MarvelService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val marvelService: MarvelService) : MarvelRepository {
    override suspend fun getCharacters(): Flow<CharacterDataWrapperResponse> = flow {
        try {
            val response = marvelService.getCharacters()
            emit(response)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getCharactersById(characterId: Int): Flow<CharacterDataWrapperResponse> = flow {
        try {
            val response = marvelService.getCharacterById(characterId)
            emit(response)
        } catch (e: Exception) {
            throw e
        }
    }
}