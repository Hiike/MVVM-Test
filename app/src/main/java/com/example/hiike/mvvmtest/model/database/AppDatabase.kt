package com.example.hiike.mvvmtest.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hiike.mvvmtest.model.Post
import com.example.hiike.mvvmtest.model.PostDao

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
}