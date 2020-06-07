package org.kotlin.exchange_data.di


import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kotlin.base_lib.BuildConfig
import org.kotlin.exchange_data.api.ExchangeService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  Class responsible for retrofit instance
 */
@Module
class ExchangeApiModule {

    @Provides
    @Reusable
    fun provideRetrofit(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()

        // add logging interceptor only for DEBUG builds
        if (BuildConfig.DEBUG)
            httpClient.addInterceptor(logging)

        return Retrofit.Builder().baseUrl(ExchangeService.ENDPOINT)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Reusable
    fun provideRetrofitService(retrofit: Retrofit): ExchangeService {
        return retrofit.create(ExchangeService::class.java)
    }
}