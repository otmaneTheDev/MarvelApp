package com.example.data.repositories

import com.example.data.service.MarvelService
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MarvelRepositoryImplTest {
    private lateinit var marvelRepositoryImpl: MarvelRepositoryImpl

    @RelaxedMockK
    lateinit var marvelService: MarvelService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        marvelRepositoryImpl = MarvelRepositoryImpl(marvelService)
    }

    @Test
    fun getCharacters() {
        runBlocking {
            marvelRepositoryImpl.getCharacters().collect {}

            coVerify { marvelService.getCharacters() }
        }
    }

    @Test
    fun getCharacterById() {
        runBlocking {
            marvelRepositoryImpl.getCharactersById(12).collect {}

            coVerify { marvelService.getCharacterById(12) }
        }
    }
}