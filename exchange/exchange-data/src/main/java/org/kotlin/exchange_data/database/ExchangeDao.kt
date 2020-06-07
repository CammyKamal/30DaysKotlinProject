package org.kotlin.exchange_data.database

import androidx.room.*
import org.kotlin.exchange_domain.model.ExchangeModel

/**
 * Exchange Dao containing Room Database Queries
 */
@Dao
abstract class ExchangeDao {
    @Query("SELECT * FROM Exchange")
    abstract fun getExchangeList(): List<ExchangeModel.ExchangeDatabaseModel>

    @Query("SELECT currencyName FROM Exchange")
    abstract fun getExchangeNames(): MutableList<String>

    @Query("SELECT currencyValue FROM Exchange where currencyName=:currencyName")
    abstract fun getExchangeValue(currencyName:String): Double

    /**
     * Sets the [exchangeData]. This method guarantees that only one
     * exchangeData record is present in the table by first deleting all table
     * data before inserting the ExchangeData.
     *
     */
    @Transaction
    open suspend fun setExhangeData(exchangeData: ExchangeModel.ExchangeDatabaseModel) {
        deleteExchangeData()
        insertExchangeData(exchangeData)
    }

    @Query("DELETE FROM Exchange")
    abstract suspend fun deleteExchangeData()

    /**
     * This method should not be used.  Instead, use [exchangeData],
     * as that method guarantees only a single [exchangeData] will reside
     * in the table.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertExchangeData(exchangeData: ExchangeModel.ExchangeDatabaseModel)
}