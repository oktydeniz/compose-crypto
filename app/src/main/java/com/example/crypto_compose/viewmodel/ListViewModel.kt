package com.example.crypto_compose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_compose.model.CryptoListItem
import com.example.crypto_compose.repository.CryptoRepository
import com.example.crypto_compose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel(){

    init {
        loadData()
    }

    var cryptoList = mutableStateOf<List<CryptoListItem>>(listOf())
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    //for search
    private var initialCryptoItem = listOf<CryptoListItem>()
    private var isSearchStarting = true

    fun loadData () {
        viewModelScope.launch {
  //          isLoading.value = true
            val result = repository.getCryptoList()
            when(result){
                is Resource.Success -> {
                    val allItems = result.data!!.mapIndexed{ index, _ -> result.data[index] } as List<CryptoListItem>
                    cryptoList.value += allItems
                    isLoading.value = false
                    error.value = ""
                }
                 // CryptoListItem(cryptoListItem.id, cryptoListItem.currency, cryptoListItem.symbol, cryptoListItem.name, cryptoListItem.logoURL, cryptoListItem.status, cryptoListItem.price, cryptoListItem.priceDate,
                //  cryptoListItem.priceTimestamp, cryptoListItem.circulatingSupply, cryptoListItem.maxSupply, cryptoListItem.marketCap, cryptoListItem)
                is Resource.Error -> {
                    isLoading.value = false
                    error.value = result.message!!
                }
            }
        }

    }

    fun searchCryptoList(q : String) {
        //if search start listToSearch value will be cryptoList.value that means we put all data in
        //and if search stop we put search results in initialCryptoItem end will show results to user
        // with  cryptoList.value = results
        val listToSearch = if (isSearchStarting) {
            cryptoList.value
        } else {
            initialCryptoItem
        }

        viewModelScope.launch(Dispatchers.Default) {
            if (q.isEmpty()){
                cryptoList.value = initialCryptoItem
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                it.currency.contains(q.trim(), ignoreCase = true)
            }

            if (isSearchStarting){
                initialCryptoItem = cryptoList.value
                isSearchStarting = false
            }
            cryptoList.value = results
        }
    }
}