package com.example.hiike.mvvmtest.ui.post

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PostListViewModelFactory @Inject constructor(private val postListViewModel: PostListViewModel): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            return postListViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}