package com.example.hiike.mvvmtest.injection.component

import com.example.hiike.mvvmtest.MVVMApp
import com.example.hiike.mvvmtest.injection.module.ActivityBuilder
import com.example.hiike.mvvmtest.injection.module.AppModule
import com.example.hiike.mvvmtest.injection.module.NetworkModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AndroidSupportInjectionModule::class,
            ActivityBuilder::class,
            AppModule::class,
            NetworkModule::class]
)
interface AppComponent {
    fun inject(app: MVVMApp)
}