package com.example.crypto_compose.dependencyinjection

import com.example.crypto_compose.repository.CryptoRepository
import com.example.crypto_compose.services.CryptoAPI
import com.example.crypto_compose.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCryptoRepository(
        api:CryptoAPI
    ) = CryptoRepository(api)

@Singleton
@Provides
fun provideCryptoAPI() :CryptoAPI {
 return Retrofit.Builder()
     .addConverterFactory(GsonConverterFactory.create())
     .baseUrl(BASE_URL)
     .build()
     .create(CryptoAPI::class.java)
}
}