package org.kotlin.exchange_data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import org.kotlin.exchange_data.database.ExchangeDao
import org.kotlin.exchange_data.database.ExchangeDatabase
import javax.inject.Singleton

/**
 *  class responsible for generating various db instances
 */
@Module
class ExchangeDbModule {
    @Provides
    @Singleton
    fun provideExchangeDataDao(db: ExchangeDatabase): ExchangeDao {
        return db.exchangeDao()
    }

    @Provides
    @Singleton
    fun provideExchangeDataDatabase(context: Context): ExchangeDatabase {
        return ExchangeDatabase.getInstance(context)
    }
}