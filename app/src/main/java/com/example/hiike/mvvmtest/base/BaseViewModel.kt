package com.example.hiike.mvvmtest.base

import androidx.lifecycle.ViewModel
import com.example.hiike.mvvmtest.injection.component.DaggerViewModelInjector
import com.example.hiike.mvvmtest.injection.module.NetworkModule
import com.example.hiike.mvvmtest.ui.post.PostListViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}