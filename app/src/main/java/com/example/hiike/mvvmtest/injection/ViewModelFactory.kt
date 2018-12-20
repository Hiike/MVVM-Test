package com.example.hiike.mvvmtest.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.appcompat.app.AppCompatActivity
import com.example.hiike.mvvmtest.model.database.AppDatabase
import com.example.hiike.mvvmtest.ui.post.PostListViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "posts").build()
            @Suppress("UNCHECKED_CAST")
            return PostListViewModel(db.postDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}