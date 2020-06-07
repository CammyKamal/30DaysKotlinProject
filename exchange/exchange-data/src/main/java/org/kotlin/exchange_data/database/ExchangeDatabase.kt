package org.kotlin.exchange_data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.kotlin.exchange_domain.model.ExchangeModel

/**
 * abstract class for creating Room Database
 */
@Database(entities = [ExchangeModel.ExchangeDatabaseModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ExchangeDatabase: RoomDatabase() {

    abstract fun exchangeDao(): ExchangeDao

    companion object {
        private const val DATABASE_NAME = "currency-db"

        // For Singleton instantiation
        @Volatile private var instance: ExchangeDatabase? = null

        fun getInstance(context: Context): ExchangeDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): ExchangeDatabase {
            return Room.databaseBuilder(
                context, ExchangeDatabase::class.java,
                DATABASE_NAME
            ).allowMainThreadQueries().build()
        }
    }
}