package org.kotlin.exchange_domain.usecases

import org.kotlin.exchange_domain.callback.ResultCallback
import org.kotlin.exchange_domain.model.ExchangeModel
import org.kotlin.exchange_domain.repo.ExchangeRepository
import javax.inject.Inject

//Use case class which will be used in viewmodel to invoke repository methods
class GetExchangeRatesUseCase @Inject constructor(private val repo: ExchangeRepository) {

    suspend operator fun invoke(base:String,callback: ResultCallback?) {
        return repo.getExchangeData(base,callback)
    }

    fun getCurrencyData():List<ExchangeModel.ExchangeDatabaseModel>{
        return repo.getDataFromDatabase()
    }

    fun getCurrencyNames():MutableList<String>{
        return repo.getCurrencyNames()
    }

    fun getCurrencyValue(currencyName:String):Double{
        return repo.getCurrencyValue(currencyName)
    }
}