package com.application.revoluttestapp.datastore

import com.application.revoluttestapp.rates.RateList
import com.application.revoluttestapp.remote.RateService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of datastore to access rates using Rest API
 */
class RateServiceDataStore @Inject constructor(private val rateService: RateService) :
    RateDatastore {

    override fun getRates(base: String): Single<RateList> {
        return rateService.getBaseRates(base)
    }
}