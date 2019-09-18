package com.application.revoluttestapp.repository

import com.application.revoluttestapp.datastore.RateServiceDataStore
import com.application.revoluttestapp.rates.RateList
import io.reactivex.Single
import javax.inject.Inject

/**
 * repository used to access rates to render the UI
 */
class RateRepo @Inject constructor(private val rateDataStore: RateServiceDataStore) {

    fun getRates(base: String): Single<RateList> {
        return rateDataStore.getRates(base)
    }
}