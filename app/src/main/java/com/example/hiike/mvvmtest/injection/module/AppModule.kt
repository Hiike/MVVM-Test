package com.example.hiike.mvvmtest.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import com.example.hiike.mvvmtest.model.PostDao
import com.example.hiike.mvvmtest.model.database.AppDatabase
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

}