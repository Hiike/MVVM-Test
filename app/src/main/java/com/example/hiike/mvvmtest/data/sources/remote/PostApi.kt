package com.example.hiike.mvvmtest.data.sources.remote

import com.example.hiike.mvvmtest.data.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {
    /**
     * Get the list of the posts from the API
     */
    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}