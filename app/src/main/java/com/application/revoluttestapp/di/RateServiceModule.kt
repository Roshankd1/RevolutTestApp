package com.application.revoluttestapp.di

import com.application.revoluttestapp.BuildConfig
import com.application.revoluttestapp.remote.RateService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger2 module used to provide Rest dependencies
 */
@Module
class RateServiceModule {

    @Singleton
    @Provides
    internal fun providesRateService(): RateService {

        val builder = Retrofit.Builder()
            .baseUrl(RateService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        val client = getOkHttpBuilder().build()
        if (client != null) {
            builder.client(client)
        }
        return builder.build().create(RateService::class.java)
    }

    private fun getOkHttpBuilder(): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor)
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
            .readTimeout(60000, TimeUnit.MILLISECONDS)
    }

}