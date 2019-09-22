package com.application.revoluttestapp.datastore

import com.application.revoluttestapp.rates.RateList
import io.reactivex.Single

interface RateDatastore {

    /**
     * Returns the rates for given base value
     */
    fun getRates(base:String): Single<RateList>
}