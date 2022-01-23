package com.example.data.di

import com.example.data.repositories.MarvelRepository
import com.example.data.repositories.MarvelRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun provideRepository(repository: MarvelRepositoryImpl): MarvelRepository

}