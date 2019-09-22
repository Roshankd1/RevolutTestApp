package com.application.revoluttestapp.remote

import com.application.revoluttestapp.rates.RateList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit2 service to implement Rates API
 */

interface RateService {

    companion object {
        const val BASE_URL = "https://revolut.duckdns.org/"
    }

    @GET("/latest")
    fun getBaseRates(@Query("base") base: String): Single<RateList>
}