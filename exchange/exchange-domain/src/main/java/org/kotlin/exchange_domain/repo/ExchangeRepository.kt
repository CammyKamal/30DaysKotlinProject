package org.kotlin.exchange_domain.repo

import org.kotlin.exchange_domain.callback.ResultCallback
import org.kotlin.exchange_domain.model.ExchangeModel


//Exchange repository interface which will be invoke via use cases
interface ExchangeRepository {
    suspend fun getExchangeData(base:String,callback: ResultCallback?)
    fun getDataFromDatabase(): List<ExchangeModel.ExchangeDatabaseModel>
    fun getCurrencyNames(): MutableList<String>
    fun getCurrencyValue(currencyName: String): Double

}