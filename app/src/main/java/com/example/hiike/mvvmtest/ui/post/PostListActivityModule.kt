package com.example.hiike.mvvmtest.ui.post

import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

@Module
class PostListActivityModule {
    @Provides
    fun providePostListViewModelFactory(factory: PostListViewModelFactory): ViewModelProvider.Factory = factory
}