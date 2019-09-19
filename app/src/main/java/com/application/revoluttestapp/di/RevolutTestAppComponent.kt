package com.application.revoluttestapp.di

import android.app.Application
import android.content.Context
import com.application.revoluttestapp.application.RevolutTestApp
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        MainActivityModule::class,
        RateServiceModule::class,
        RateRepoModule::class]
)
interface RevolutTestAppComponent {

    fun inject(application: RevolutTestApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): RevolutTestAppComponent
    }
}