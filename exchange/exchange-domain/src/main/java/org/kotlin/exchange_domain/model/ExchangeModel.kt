package org.kotlin.exchange_domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//class for handling data model for Api response
sealed class ExchangeModel {

    @Entity(tableName = "Exchange")
    data class ExchangeDatabaseModel(
        @ColumnInfo(name = "currencyName")
        @PrimaryKey
        var currencyName: String,
        @ColumnInfo(name = "currencyValue")
        var currencyValue: Double
    )
}