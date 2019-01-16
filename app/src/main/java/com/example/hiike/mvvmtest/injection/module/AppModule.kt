package com.example.hiike.mvvmtest.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import com.example.hiike.mvvmtest.data.sources.PostRepositoryImp
import com.example.hiike.mvvmtest.data.sources.local.PostDao
import com.example.hiike.mvvmtest.data.sources.local.AppDatabase
import com.example.hiike.mvvmtest.data.sources.remote.PostApi
import com.example.hiike.mvvmtest.domain.PostRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun providePostDatabase(app: Application): AppDatabase = Room.databaseBuilder(app, AppDatabase::class.java, "posts").build()

    @Provides
    @Singleton
    fun providePostDao(database: AppDatabase): PostDao = database.postDao()

    @Provides
    @Singleton
    fun providePostListRepository(postApi: PostApi, postDao: PostDao): PostRepository = PostRepositoryImp(postApi, postDao)
}