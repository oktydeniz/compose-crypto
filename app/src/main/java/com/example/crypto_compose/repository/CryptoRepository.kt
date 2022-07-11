package com.example.crypto_compose.repository

import com.example.crypto_compose.model.CryptoListItem
import com.example.crypto_compose.model.CryptoModel
import com.example.crypto_compose.services.CryptoAPI
import com.example.crypto_compose.util.Constants.API_KEY
import com.example.crypto_compose.util.Constants.CALL_ATTRIBUTES
import com.example.crypto_compose.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api : CryptoAPI
) {
    suspend fun getCryptoList() :Resource<CryptoModel> {

        val response = try {
            api.getAllList(API_KEY)

        } catch (e: Exception) {
            return Resource.Error("Error : ")
        }
        return Resource.Success(response)
    }

    suspend fun getDetailCrypto (id: String) : Resource<CryptoListItem> {
        val response = try {
            api.getDetailCrypto(API_KEY, id, CALL_ATTRIBUTES)
        } catch (e: Exception){
            return Resource.Error("Error : ")
        }
        return Resource.Success(response)
    }
}