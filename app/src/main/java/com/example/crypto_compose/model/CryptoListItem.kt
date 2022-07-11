package com.example.crypto_compose.model

import com.google.gson.annotations.SerializedName

data class CryptoListItem (
    val id: String,
    val currency: String,
    val symbol: String,
    val name: String,

    @SerializedName("logo_url")
    val logoURL: String,

    val status: String,
    val price: String,

    @SerializedName("price_date")
    val priceDate: String,

    @SerializedName("price_timestamp")
    val priceTimestamp: String,

    @SerializedName("circulating_supply")
    val circulatingSupply: String,

    @SerializedName("max_supply")
    val maxSupply: String,

    @SerializedName("market_cap")
    val marketCap: String,

    @SerializedName("market_cap_dominance")
    val marketCapDominance: String,

    @SerializedName("num_exchanges")
    val numExchanges: String,

    @SerializedName("num_pairs")
    val numPairs: String,

    @SerializedName("num_pairs_unmapped")
    val numPairsUnmapped: String,

    @SerializedName("first_candle")
    val firstCandle: String,

    @SerializedName("first_trade")
    val firstTrade: String,

    @SerializedName("first_order_book")
    val firstOrderBook: String,

    val rank: String,

    @SerializedName("rank_delta")
    val rankDelta: String,

    val high: String,

    @SerializedName("high_timestamp")
    val highTimestamp: String,

    @SerializedName("1d")
    val the1D: The1_D,

    @SerializedName("7d")
    val the7D: The1_D,

    @SerializedName("30d")
    val the30D: The1_D,

    @SerializedName("365d")
    val the365D: The1_D,

    val ytd: The1_D
)

data class The1_D (
    val volume: String,

    @SerializedName("price_change")
    val priceChange: String,

    @SerializedName("price_change_pct")
    val priceChangePct: String,

    @SerializedName("volume_change")
    val volumeChange: String,

    @SerializedName("volume_change_pct")
    val volumeChangePct: String,

    @SerializedName("market_cap_change")
    val marketCapChange: String,

    @SerializedName("market_cap_change_pct")
    val marketCapChangePct: String
)
