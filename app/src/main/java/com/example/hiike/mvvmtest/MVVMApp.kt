package com.example.hiike.mvvmtest

import android.app.Activity
import android.app.Application
import com.example.hiike.mvvmtest.injection.component.DaggerAppComponent
import com.example.hiike.mvvmtest.injection.module.AppModule
import com.example.hiike.mvvmtest.injection.module.NetworkModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MVVMApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityInjector
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build().inject(this)
    }
}