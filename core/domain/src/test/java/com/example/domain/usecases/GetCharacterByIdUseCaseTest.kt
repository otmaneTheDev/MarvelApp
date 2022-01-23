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

class GetCharacterByIdUseCaseTest {
    private lateinit var getCharacterByIdUseCase: GetCharacterByIdUseCase

    @RelaxedMockK
    lateinit var repositoryImpl: MarvelRepositoryImpl

    @RelaxedMockK
    lateinit var mapper: Mapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCharacterByIdUseCase = GetCharacterByIdUseCase(repositoryImpl, mapper)
    }

    @Test
    fun getCharactersById() {
        runBlocking {
            getCharacterByIdUseCase.getCharacterById(12).collect {}

            coVerify { repositoryImpl.getCharactersById(13) }
        }
    }
}