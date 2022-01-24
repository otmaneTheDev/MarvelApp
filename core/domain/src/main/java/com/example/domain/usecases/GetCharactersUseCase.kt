package com.example.domain.usecases

import com.example.data.repositories.MarvelRepositoryImpl
import com.example.domain.mappers.Mapper
import com.example.domain.models.CharacterDataContainer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repositoryImpl: MarvelRepositoryImpl, private val mapper: Mapper) {

    suspend fun getCharacters(): Flow<CharacterDataContainer> = flow {
        repositoryImpl.getCharacters(0).catch { error ->
            throw error
        }.collect {
            val mappedResponse = mapper.map(it.characterDataContainer)
            emit(mappedResponse)
        }
    }
}