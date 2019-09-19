package com.application.revoluttestapp.application

import android.app.Activity
import android.app.Application
import com.application.revoluttestapp.di.DaggerRevolutTestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class RevolutTestApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatcher: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatcher
    }

    override fun onCreate() {
        super.onCreate()

        DaggerRevolutTestAppComponent
            .builder()
            .application(this)
            .context(this)
            .build()
            .inject(this)
    }
}