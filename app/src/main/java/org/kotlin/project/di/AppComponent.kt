package org.kotlin.project.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import org.kotlin.base_lib.di.CoreModule
import org.kotlin.exchange_data.di.ExchangeApiModule
import org.kotlin.exchange_data.di.ExchangeDbModule
import org.kotlin.exchange_data.di.ExchangeModule
import org.kotlin.exchange_ui.di.ExchangeFragmentModule
import org.kotlin.exchange_ui.di.ExchangeViewModelModule
import org.kotlin.project.CurrencyApplication
import javax.inject.Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ExchangeFragmentModule::class, ExchangeViewModelModule::class,CoreModule::class, ExchangeApiModule::class, ExchangeDbModule::class, ExchangeModule::class]
)
@Singleton
interface AppComponent : AndroidInjector<CurrencyApplication> {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun setContext(context: Context): Builder
    }
}