package com.application.revoluttestapp.di

import com.application.revoluttestapp.domain.RateUseCase
import dagger.Module
import dagger.Provides
import presenter.MainPresenter
import javax.inject.Singleton

@Module
class MainPresenterModule {

    @Singleton
    @Provides
    internal fun providesMainPresenter(rateUseCase: RateUseCase): MainPresenter {
        return MainPresenter(rateUseCase)
    }
}