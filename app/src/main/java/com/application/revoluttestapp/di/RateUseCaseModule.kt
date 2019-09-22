package com.application.revoluttestapp.di

import com.application.revoluttestapp.domain.RateUseCase
import com.application.revoluttestapp.repository.RateRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RateUseCaseModule {

    @Singleton
    @Provides
    internal fun providesRateUseCase(rateRepo: RateRepo): RateUseCase {
        return RateUseCase(rateRepo)
    }
}