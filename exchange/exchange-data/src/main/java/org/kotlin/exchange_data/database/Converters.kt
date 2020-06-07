package org.kotlin.exchange_data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.kotlin.exchange_domain.model.ExchangeModel

/**
 *  Type Converter class to transform rates json object into Room table structure
 */
class Converters {

    //converters for Experience list
    @TypeConverter
    fun stringToExchangeList(data: String?): List<ExchangeModel.ExchangeDatabaseModel> {
        val listType = object : TypeToken<ExchangeModel.ExchangeDatabaseModel>() {
        }.type
        return Gson().fromJson(data, listType)
    }

    @TypeConverter
    fun experienceListToString(someObjects: List<ExchangeModel.ExchangeDatabaseModel>): String {
        return Gson().toJson(someObjects)
    }

}