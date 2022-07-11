package com.example.crypto_compose.services

import com.example.crypto_compose.model.CryptoListItem
import com.example.crypto_compose.model.CryptoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPI {

    @GET("currencies/ticker")
    suspend fun getAllList(
        @Query("key") key :String
    ) : CryptoModel

    @GET("currencies/ticker")
    suspend fun getDetailCrypto(
        @Query("key") key :String,
        @Query("ids") id :String,
        @Query("attributes") attributes :String
    ) :CryptoListItem
}