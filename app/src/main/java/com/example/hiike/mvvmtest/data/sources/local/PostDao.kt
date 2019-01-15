package com.example.hiike.mvvmtest.data.sources.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.hiike.mvvmtest.data.Post
import io.reactivex.Single

@Dao
interface PostDao {
    @get:Query("SELECT * FROM post")
    val all: Single<List<Post>>

    @Insert
    fun insertAll(vararg posts: Post)

    @Query("DELETE FROM post")
    fun deleteAllPosts()
}