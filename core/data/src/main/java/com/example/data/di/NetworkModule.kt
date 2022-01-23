package com.example.data.di

import com.example.data.service.MarvelService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://gateway.marvel.com/"
        private const val PUBLIC_API_KEY = "94f57e588fce1a284d91bf0caf4f4d5c"
        private const val PRIVATE_API_KEY = "9836c2bbd41b1eb97146eb8f80e01f203ce20e36"
    }

    @Provides
    fun provideMarvelService(okHttpClient: OkHttpClient): MarvelService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelService::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()

        val loggerInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val timestamp = System.currentTimeMillis().toString()
        val hashedApiKey = getHashKey(timestamp, PRIVATE_API_KEY, PUBLIC_API_KEY)

        val apiKeyInterceptor = Interceptor { chain ->
            val url = chain.request().url.newBuilder()
                .addQueryParameter("apikey", PUBLIC_API_KEY)
                .addQueryParameter("hash", hashedApiKey)
                .addQueryParameter("ts", timestamp)
                .build()

            val request = chain.request().newBuilder().url(url).build()
            chain.proceed(request)
        }

        httpClientBuilder.addInterceptor(loggerInterceptor)
        httpClientBuilder.addInterceptor(apiKeyInterceptor)

        return httpClientBuilder.build()
    }

    private fun getHashKey(timestamp: String, privateKey: String, publicKey: String): String {
        val input = timestamp + privateKey + publicKey
        return md5(input)
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}