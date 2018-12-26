package com.example.hiike.mvvmtest.injection.module

import com.example.hiike.mvvmtest.ui.post.PostListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributePostListActivity(): PostListActivity
}