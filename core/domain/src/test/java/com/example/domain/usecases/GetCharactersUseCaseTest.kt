package com.example.domain.usecases

import com.example.data.repositories.MarvelRepositoryImpl
import com.example.domain.mappers.Mapper
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @RelaxedMockK
    lateinit var repositoryImpl: MarvelRepositoryImpl

    @RelaxedMockK
    lateinit var mapper: Mapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCharactersUseCase = GetCharactersUseCase(repositoryImpl, mapper)
    }

    @Test
    fun getCharacters() {
        runBlocking {
            getCharactersUseCase.getCharacters().collect {}

            coVerify { repositoryImpl.getCharacters() }
        }
    }
}