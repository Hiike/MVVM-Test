package com.example.hiike.mvvmtest

import android.app.Application
import timber.log.Timber

class MVVMApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}