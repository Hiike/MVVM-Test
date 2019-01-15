package com.example.hiike.mvvmtest.data.sources.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.hiike.mvvmtest.data.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
}