package org.kotlin.exchange_data

import android.content.Context
import com.google.gson.JsonObject
import org.kotlin.base_lib.utility.Utility
import org.kotlin.exchange_data.api.ExchangeService
import org.kotlin.exchange_data.database.ExchangeDao
import org.kotlin.exchange_domain.callback.ResultCallback
import org.kotlin.exchange_domain.model.ExchangeModel
import org.kotlin.exchange_domain.repo.ExchangeRepository
import javax.inject.Inject

/**
 *  Class containing implementation for ExchangeRepository
 */
class ExchangeRepositoryImpl @Inject constructor(
    private val exchangeService: @JvmSuppressWildcards ExchangeService,
    private val exchangeDataDao: @JvmSuppressWildcards ExchangeDao
) :
    ExchangeRepository {

    @Inject
    lateinit var context: Context
    private val RATES: String = "rates"

    /**
     * fetches data from local store first. if not available locally, fetches from server
     */
    override suspend fun getExchangeData(
        base: String,
        callback: ResultCallback?
    ) {
        return fetchFromRemoteSource(base, callback)
    }

    override fun getDataFromDatabase(): List<ExchangeModel.ExchangeDatabaseModel> {
        return exchangeDataDao.getExchangeList()
    }

    override fun getCurrencyNames(): MutableList<String> {
        return exchangeDataDao.getExchangeNames()
    }

    override fun getCurrencyValue(currencyName: String): Double {
        return exchangeDataDao.getExchangeValue(currencyName)
    }

    private suspend fun fetchFromRemoteSource(base: String, callback: ResultCallback?) {
        try {
            if (!Utility.isNetworkAvailable(context)) {
                callback?.failure()
            } else {
                val response = exchangeService.getExchangeList(base)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        val exchangeObject = response.body() as JsonObject
                        val ratesObject = exchangeObject.getAsJsonObject(RATES)
                        ratesObject.keySet().forEach { keyStr ->
                            val keyvalue: Any = ratesObject.get(keyStr)
                            exchangeDataDao.insertExchangeData(
                                ExchangeModel.ExchangeDatabaseModel(
                                    keyStr,
                                    keyvalue.toString().toDouble()
                                )
                            )
                        }
                        callback?.success()
                    }
                } else {
                    callback?.failure()
                }
            }

        } catch (e: Exception) {
            callback?.failure()
        }
    }
}