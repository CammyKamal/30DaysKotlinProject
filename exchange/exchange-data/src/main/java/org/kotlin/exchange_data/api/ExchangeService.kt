package org.kotlin.exchange_data.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  Exchange Api Service class containing Api End points and Base Url
 */
interface ExchangeService {
    @GET("latest?")
    suspend fun getExchangeList(@Query("base")base:String): Response<JsonObject>

    companion object {
        const val ENDPOINT = "https://api.exchangeratesapi.io/"
    }
}