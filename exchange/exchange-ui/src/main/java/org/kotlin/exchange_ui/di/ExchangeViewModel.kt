package org.kotlin.exchange_ui.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import org.kotlin.base_lib.ViewModelKey
import org.kotlin.exchange_ui.currency.CurrencyConverterFragment
import org.kotlin.exchange_ui.currency.CurrencyConverterViewModel
import org.kotlin.exchange_ui.splash.SplashFragment
import org.kotlin.exchange_ui.splash.SplashViewModel

//Modules responsible for factory classes of Views and ViewModel
@Module
interface ExchangeViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyConverterViewModel::class)
    fun bindCurrencyViewModel(viewModel: CurrencyConverterViewModel): ViewModel
}

@Module
interface ExchangeFragmentModule {
    @ContributesAndroidInjector(modules = [ExchangeViewModelModule::class])
    fun splashFragment(): SplashFragment

    @ContributesAndroidInjector(modules = [ExchangeViewModelModule::class])
    fun currencyConvertorFragment(): CurrencyConverterFragment
}