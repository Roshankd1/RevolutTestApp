package com.application.revoluttestapp.di

import com.application.revoluttestapp.datastore.RateServiceDataStore
import com.application.revoluttestapp.remote.RateService
import com.application.revoluttestapp.repository.RateRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RateRepoModule {

    @Singleton
    @Provides
    internal fun providesRateDataStore(rateservice: RateService): RateServiceDataStore {
        return RateServiceDataStore(rateservice)
    }

    @Singleton
    @Provides
    internal fun providesRateRepository(rateDatastore: RateServiceDataStore): RateRepo {
        return RateRepo(rateDatastore)
    }
}