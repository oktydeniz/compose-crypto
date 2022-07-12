package com.example.crypto_compose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.crypto_compose.model.CryptoListItem
import com.example.crypto_compose.model.CryptoModel
import com.example.crypto_compose.repository.CryptoRepository
import com.example.crypto_compose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CryptoRepository
) :ViewModel(){

   suspend fun getCrypto(id: String) : Resource<CryptoModel> {
        return repository.getDetailCrypto(id)
   }

}