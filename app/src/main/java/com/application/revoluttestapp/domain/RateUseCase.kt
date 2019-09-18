package com.application.revoluttestapp.domain

import com.application.revoluttestapp.rates.RateList
import com.application.revoluttestapp.repository.RateRepo
import io.reactivex.Single
import javax.inject.Inject

class RateUseCase @Inject constructor(private val rateRepo: RateRepo) {

    fun getRates(base: String): Single<RateList> {
        return rateRepo.getRates(base)
    }
}