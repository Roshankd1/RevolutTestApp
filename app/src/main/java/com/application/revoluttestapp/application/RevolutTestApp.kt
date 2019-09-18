package com.application.revoluttestapp.application

import android.app.Application
import com.application.revoluttestapp.di.DaggerRevolutTestAppComponent


class RevolutTestApp : Application() {

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