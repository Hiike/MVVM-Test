package com.example.hiike.mvvmtest.domain

import com.example.hiike.mvvmtest.data.Post
import io.reactivex.Observable

interface PostRepository {

    fun getPosts() : Observable<List<Post>>

}