package com.example.crypto_compose.util

object Constants {
    //https://api.nomics.com/v1/currencies/ticker?key=578980bdfd81119e47c8db1b1f50fd4be8a203f3
    //https://api.nomics.com/v1/currencies/ticker?key=578980bdfd81119e47c8db1b1f50fd4be8a203f3&ids=BTC&attributes=id,name,logo_url
    //https://api.nomics.com/v1/currencies/ticker?key=578980bdfd81119e47c8db1b1f50fd4be8a203f3&ids=BTC,ETH,XRP&interval=1d,30d&convert=EUR&platform-currency=ETH&per-page=100&page=1

    const val API_KEY = "578980bdfd81119e47c8db1b1f50fd4be8a203f3"
    const val BASE_URL = "https://api.nomics.com/v1/"
    const val CALL_ATTRIBUTES = "id,name,logo_url"
}