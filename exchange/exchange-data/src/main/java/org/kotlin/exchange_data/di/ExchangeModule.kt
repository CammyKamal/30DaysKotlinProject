package org.kotlin.exchange_data.di

import dagger.Binds
import dagger.Module
import org.kotlin.exchange_data.ExchangeRepositoryImpl
import org.kotlin.exchange_domain.repo.ExchangeRepository

/**
 *  interface for generating factory class for ExchangeRepositoryImpl
 */
@Module
interface ExchangeModule {
    @Binds
    fun bindExchangeRepository(exchangeRepositoryImpl: ExchangeRepositoryImpl) : ExchangeRepository
}