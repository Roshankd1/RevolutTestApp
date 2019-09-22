package com.application.revoluttestapp.di

import com.application.revoluttestapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    internal abstract fun providesMainActivityModule(): MainActivity
}